package com.studentManager.user.service.Impl;

import com.studentManager.user.config.MyConfirmCallback;
import com.studentManager.user.config.MyReturnCallback;
import com.studentManager.user.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
@Component
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MyConfirmCallback myConfirmCallback;

    @Autowired
    private MyReturnCallback myReturnCallback;

    // 发送到简单队列
    public void sendToSimpleQueue(String message) {
        // 创建消息
        Message build = MessageBuilder
                .withBody(message.getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000")
                .build();
        rabbitTemplate.setConfirmCallback(myConfirmCallback);
        rabbitTemplate.setReturnCallback(myReturnCallback);
        CorrelationData correlationData = new CorrelationData("type1_" + UUID.randomUUID().toString());
       // CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString() + "@" + "simpleQueue");
        rabbitTemplate.convertAndSend(RabbitMQConfig.SIMPLE_QUEUE, build, correlationData);
        //rabbitTemplate.waitForConfirms();
        System.out.println("Sent message to simpleQueue: " + message);
    }

    // 发送到直连交换机
    public void sendToDirectExchange(String message) {
        Message build = MessageBuilder
                .withBody(message.getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000")
                .build();
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString() + "@" + RabbitMQConfig.DIRECT_EXCHANGE);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY, build, correlationData);
        System.out.println("Sent message to directExchange with key " + RabbitMQConfig.DIRECT_ROUTING_KEY + ": " + message);
    }

    // 发送到主题交换机
    public void sendToTopicExchange(String message) {
        Message build = MessageBuilder
                .withBody(message.getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000")
                .build();
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString() + "@" + RabbitMQConfig.TOPIC_EXCHANGE);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.TOPIC_ROUTING_KEY, build, correlationData);
        System.out.println("Sent message to topicExchange with key " + RabbitMQConfig.TOPIC_ROUTING_KEY + ": " + message);
    }

    // 发送到扇出交换机（Fanout，不需要路由键）
    public void sendToFanoutExchange(String message) {
        Message build = MessageBuilder
                .withBody(message.getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000")
                .build();
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString() + "@" + RabbitMQConfig.FANOUT_EXCHANGE);
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", build, correlationData);
        System.out.println("Sent message to fanoutExchange: " + message);
    }


    public void simple(String message) {
        Message build = MessageBuilder
                .withBody(message.getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000")
                .build();
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString() + "@" + RabbitMQConfig.FANOUT_EXCHANGE);
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", build, correlationData);
        System.out.println("Sent message to fanoutExchange: " + message);
    }

//     // 发送到简单队列
//     public void sendToSimpleQueue(String message) {
//         amqpTemplate.convertAndSend(RabbitMQConfig.SIMPLE_QUEUE, message);
//         System.out.println("Sent message to simpleQueue: " + message);
//     }
//
//     // 发送到直连交换机
//     public void sendToDirectExchange(String message) {
//         amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY, message);
//         System.out.println("Sent message to directExchange with key " + RabbitMQConfig.DIRECT_ROUTING_KEY + ": " + message);
//     }
//
//     // 发送到主题交换机
//     public void sendToTopicExchange(String message) {
//         amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.TOPIC_ROUTING_KEY, message);
//         System.out.println("Sent message to topicExchange with key " + RabbitMQConfig.TOPIC_ROUTING_KEY + ": " + message);
//     }
//
//     // 发送到扇出交换机（Fanout，不需要路由键）
//     public void sendToFanoutExchange(String message) {
//         amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message);
//         System.out.println("Sent message to fanoutExchange: " + message);
//     }

//    @RabbitListener(queues = "simpleQueue", ackMode = "MANUAL")
//    public void receiveMessageFromSimpleQueue(String message, Channel channel, Message amqpMessage) throws IOException {
//        try {
//            System.out.println("进入 simpleQueue队列");
//            Thread.sleep(6000);
//            // 判断条件，假设不满足的条件为 message.equals("bad")
//            if (!"bad".equals(message)) {
//                // 如果消息不符合条件，拒绝并将其放入死信队列
//                channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
//                return;
//            }
//
//            // 正常处理消息
//            System.out.println("Received message from simpleQueue: " + message);
//            channel.basicQos(0, 1, false);
//            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            // 发生异常时，将消息转移到死信队列
//            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
//            // 如果处理失败，则拒绝消息，第三个参数true表示是否重新入队
//            // channel.basicNack(deliveryTag, false, true);
//            // 处理失败，拒绝消息，不会重新入队，将进入死信队列
//            // channel.basicReject(deliveryTag, false);
//        }
//    }
//
//    @RabbitListener(queues = "directQueue", ackMode = "MANUAL")
//    public void receiveMessageFromDirectQueue(Object message, Channel channel, Message amqpMessage) throws IOException {
//        try {
//            // 将消息转换为字符串
//            byte[] body = amqpMessage.getBody();
//            // 将消息转换为字符串
//            String msg = new String(body, StandardCharsets.UTF_8);
//            Thread.sleep(6000);
//            System.out.println("Received message from directQueue: " + msg);
//            channel.basicQos(0, 1, false);
//            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
//        }
//    }
//
//
//    @RabbitListener(queues = "topicQueue")
//    public void receiveMessageFromTopicQueue(Object message, Channel channel, Message amqpMessage) throws IOException {
//        try {
//            byte[] body = amqpMessage.getBody();
//            // 将消息转换为字符串
//            String msg = new String(body, StandardCharsets.UTF_8);
//            Thread.sleep(6000);
//            System.out.println("Received message from topicQueue: " + msg);
//            channel.basicQos(0, 1, false);
//            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
//        }
//    }
}
