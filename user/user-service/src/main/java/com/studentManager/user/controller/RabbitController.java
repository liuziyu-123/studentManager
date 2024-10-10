package com.studentManager.user.controller;


import com.rabbitmq.client.Channel;
import com.studentManager.user.config.MyConfirmCallback;
import com.studentManager.user.config.MyReturnCallback;
import com.studentManager.user.config.RabbitMQConfig;
import com.studentManager.user.service.Impl.RabbitMQService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/user/rabbit")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQService messageService;

    @Autowired
    private MyConfirmCallback myConfirmCallback;

    @Autowired
    private MyReturnCallback myReturnCallback;

    @GetMapping("work")
    public void work() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("", "queue1", i + " work队列");
        }

    }

    /**
     * 发布订阅模式
     */
    @GetMapping("fanout")
    public void fanout() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("fanoutExchange", "", i + " pubsub订阅模式");
        }

    }


    /**
     * 定向模式
     */
    @GetMapping("direct")
    public void direct() {
        List<String> routingkey = new LinkedList<>();
        routingkey.add("aaa");
        routingkey.add("bbb");
        routingkey.add("ccc");
        for (int i = 0; i < 10; i++) {

            rabbitTemplate.convertAndSend("directExchange", routingkey.get(i % 3), routingkey.get(i % 3));
        }

    }


    /**
     * topic模式
     */
    @GetMapping("topic")
    public void topic() {
        List<String> routingkey = new LinkedList<>();
        routingkey.add("boot.love");
        routingkey.add("ttt.#");
        routingkey.add("wrr.love");
        routingkey.add("boot.33");
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if (b) {
                    System.out.println("confirm 方法被执行了");
                } else {
                    System.out.println("confirm 方法没有被执行了");
                }


            }
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("消息无法路由：" + message.getBody() + "，原因：" + replyText);
            // 可以进行重试
        });

        rabbitTemplate.convertAndSend("topicExchange", routingkey.get(0), routingkey.get(0));
//        for (int i = 0; i < 4; i++) {
//            rabbitTemplate.convertAndSend("topicExchange",routingkey.get(i%4),routingkey.get(i%4));
//        }


    }


    /**
     * topic模式
     */
    @GetMapping("ttl")
    public void ttl() {
        List<String> routingkey = new LinkedList<>();
        routingkey.add("boot.love");
        routingkey.add("ttt.#");
        routingkey.add("wrr.love");
        routingkey.add("boot.33");
        //设置消息过期时间
//        MessagePostProcessor messagePostProcessor=new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration("2000");
//                return message;
//            }
//        };
//        rabbitTemplate.convertAndSend("ttlExchange",routingkey.get(0),routingkey.get(0),messagePostProcessor);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.setConfirmCallback(myConfirmCallback);
            rabbitTemplate.setReturnCallback(myReturnCallback);
            rabbitTemplate.convertAndSend("ttlExchange", routingkey.get(0), routingkey.get(0));

        }


//        rabbitTemplate.convertAndSend("directExchange","bbb",routingkey.get(0));


    }


    @GetMapping("dead")
    public void dead() {
        List<String> routingkey = new LinkedList<>();
        routingkey.add("boot.love");
        routingkey.add("ttt.#");
        routingkey.add("wrr.love");
        routingkey.add("boot.33");
        //设置消息过期时间
//        MessagePostProcessor messagePostProcessor=new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration("2000");
//                return message;
//            }
//        };
//        rabbitTemplate.convertAndSend("ttlExchange",routingkey.get(0),routingkey.get(0),messagePostProcessor);

        rabbitTemplate.convertAndSend("deadExchange", "dead", routingkey.get(0));

//        rabbitTemplate.convertAndSend("directExchange","bbb",routingkey.get(0));


    }


    @GetMapping("simpleQueue")
    public void simpleQueue(Channel channel) throws Exception {
        //创建消费者, 并覆盖设置消息处理

        Message message =  rabbitTemplate.receive("simpleQueue");
        if (message != null) {
            String body = new String(message.getBody());
            Thread.sleep(6000);
            System.out.println("Received message: " + body);
          //  channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            // 在此处处理消息
        } else {
            System.out.println("No messages available");
        }

    }

    @GetMapping("dlq")
    public void dlq(Channel channel) throws Exception {
        //创建消费者, 并覆盖设置消息处理

        Message message =  rabbitTemplate.receive("dlq");
        if (message != null) {
            Thread.sleep(6000);
            String body = new String(message.getBody());
            System.out.println("Received message: " + body);
          //  channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            // 在此处处理消息
        } else {
            System.out.println("No messages available");
        }

    }


    /**
     * 发送
     *
     * @param simpleQueue
     * @param directExchange
     * @param topicExchange
     * @param fanoutExchange
     */
    @GetMapping("/sendMsg")
    public void sendMsg(String simpleQueue, String directExchange, String topicExchange, String fanoutExchange) {
        messageService.sendToSimpleQueue(simpleQueue);
//        messageService.sendToDirectExchange(directExchange);
//        messageService.sendToTopicExchange(topicExchange);
//        messageService.sendToFanoutExchange(fanoutExchange);
    }

    /**
     * 获取死信队列信息列表（不消费）
     */
    @GetMapping("/peekMessagesFromDLQ")
    public List<Object> peekMessagesFromDLQ(@RequestParam String queueName) {
        List<Object> messages = new ArrayList<>();
        List<Object> list = new ArrayList<>();

        while (true) {
            Message message = rabbitTemplate.receive(queueName);
            if (message != null) {
                // 保存消息内容和消息元数据
                messages.add(message);
                String msg = new String(message.getBody(), StandardCharsets.UTF_8);
                list.add(msg);
            } else {
                break;
            }
        }

        // 将消息重新插入队列，确保消息不会被丢失
        for (Object msg : messages) {
            rabbitTemplate.send(queueName, (Message) msg);
        }

        // 返回消息列表
        return list;
    }


    /**
     * 消费指定条数消息
     *
     * @param queueName
     * @param messageCount
     */
    @GetMapping("/getMessagesFromDLQ")
    public List<Object> getMessagesFromDLQ(@RequestParam(value = "queueName", required = false) String queueName,
                                           @RequestParam(value = "messageCount", required = false) Integer messageCount) {
        List<Object> messages = new ArrayList<>();
        int count = 0;

        // 如果队列名称为null，使用默认的死信队列名称
        if (queueName == null) {
            queueName = RabbitMQConfig.DLQ;
        }

        // 不断从指定的死信队列中获取消息，直到队列为空或达到指定数量
        while (messageCount == null || count < messageCount) {
            // 从队列中获取消息
            Message message = rabbitTemplate.receive(queueName);

            if (message != null) {
                String msg = new String(message.getBody(), StandardCharsets.UTF_8);
                messages.add(msg);
                count++;
            } else {
                break; // 队列中没有更多消息
            }
        }

        return messages;
    }

    /**
     * 消费目标消息，将其他重新放回队列
     */
    @PostMapping("/consumeMessageFromDLQ")
    public void consumeMessageFromDLQ(@RequestParam String queueName, @RequestParam String param) {
        while (true) {
            Message message = rabbitTemplate.receive(queueName);

            if (message != null) {
                String body = new String(message.getBody());

                if (body.contains(param)) {
                    // 消费并确认此消息，此处进行处理逻辑
                    System.out.println("====消费");
                    break;
                } else {
                    // 如果不是目标消息，将其重新放回队列
                    rabbitTemplate.send(queueName, message);
                }
            } else {
                break;
            }
        }
    }

    // @GetMapping("/peekMessagesFromDLQ")
    // @ApiOperation("获取死信队列信息列表（不消费）")
    // public List<Object> peekMessagesFromDLQ(@RequestParam String queueName) {
    //     List<Object> messages = new ArrayList<>();
    //
    //     while (true) {
    //         Message message = rabbitTemplate.receive(queueName);
    //         if (message != null) {
    //             // 保存消息内容和消息元数据
    //             messages.add(message);
    //         } else {
    //             break;
    //         }
    //     }
    //
    //     // 将消息重新插入队列，确保消息不会被丢失
    //     for (Object msg : messages) {
    //         rabbitTemplate.send(queueName, (Message) msg);
    //     }
    //
    //     // 返回消息列表
    //     return messages.stream()
    //             .map(m -> rabbitTemplate.getMessageConverter().fromMessage((Message) m))
    //             .collect(Collectors.toList());
    // }
    //
    //
    // @GetMapping("/getMessagesFromDLQ")
    // @ApiOperation("消费指定条数消息")
    // public List<Object> getMessagesFromDLQ(@RequestParam(value = "queueName", required = false) String queueName,
    //                                        @RequestParam(value = "messageCount", required = false) Integer messageCount) {
    //     List<Object> messages = new ArrayList<>();
    //     int count = 0;
    //
    //     // 如果队列名称为null，使用默认的死信队列名称
    //     if (queueName == null) {
    //         queueName = RabbitMQConfig.DLQ;
    //     }
    //
    //     // 不断从指定的死信队列中获取消息，直到队列为空或达到指定数量
    //     while (messageCount == null || count < messageCount) {
    //         // 从队列中获取消息
    //         Message message = rabbitTemplate.receive(queueName);
    //
    //         if (message != null) {
    //             messages.add(rabbitTemplate.getMessageConverter().fromMessage(message));
    //             count++;
    //         } else {
    //             break; // 队列中没有更多消息
    //         }
    //     }
    //
    //     return messages;
    // }
    //
    // @PostMapping("/consumeMessageFromDLQ")
    // @ApiOperation("消费目标消息，将其他重新放回队列")
    // public void consumeMessageFromDLQ(@RequestParam String queueName, @RequestParam String param) {
    //     while (true) {
    //         Message message = rabbitTemplate.receive(queueName);
    //
    //         if (message != null) {
    //             // String body = rabbitTemplate.getMessageConverter().fromMessage(message).toString();
    //             String body = new String(message.getBody());
    //
    //             if (body.contains(param)) {
    //                 // 消费并确认此消息，此处进行处理逻辑
    //                 System.out.println("====消费");
    //                 break;
    //             } else {
    //                 // 如果不是目标消息，将其重新放回队列
    //                 rabbitTemplate.send(queueName, message);
    //             }
    //         } else {
    //             break;
    //         }
    //     }
    // }


}
