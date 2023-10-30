package com.studentManager.user.controller;

import com.rabbitmq.client.*;
import com.studentManager.common.Utils.ApiResult;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("producer")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("createMessage")
    public ApiResult createMessage() throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * queue :队列名称
         * durable :是否持久化  当mq 重新启动之后，还在
         * exclusive:是否独立。只能用一个消费这监听这队列  当Connection 关闭时，是否删除队列
         * autoDelete :是否自动删除。当没有 Consume 时，自动删除掉
         * arguments :参数
         */

        channel.queueDeclare("hello",true,false,false,null);
        String body="张玉敏，我爱你";
        /**
         * basicPublish 参数
         * 1.exchange :交换机名称
         * 2.routingkey:路由名称
         * 3.props:配置信息
         * 4.body:发送信息数据
         */
        channel.basicPublish("","hello",null,body.getBytes());
        channel.close();
        connection.close();
       return ApiResult.success();
    }


    @GetMapping("workQueue")
    public ApiResult workQueue() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory=new ConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("work_queues",true,false,false,null);
        for (int i = 0; i < 20; i++) {
            String body =i+"work_queues";
            Thread.sleep(2000);
            channel.basicPublish("","work_queues",null,body.getBytes());
        }
        channel.close();
        connection.close();
        return ApiResult.success();
    }

    @GetMapping("pubsub")
    public ApiResult pubsub() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory=new ConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * 1.exchange  交换机名称
         * 2.type  交换机类型
         *       direct  定向
         *       fanout   广播
         *       topic  通配符方式
         *       headers  参数匹配
         * 3.durable   是否持久化
         * 4.autoDelete  自动删除
         * 5.internal   内部使用  一般false
         * 6.arguments  参数
         */

        String exchangeName="firstExchange";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true,false,false,null);
        //创建队列
        String queue1="firstQueue";
        String queue2="secordQueue";
        channel.queueDeclare(queue1,true,false,false,null);
        channel.queueDeclare(queue2,true,false,false,null);
        /**
         * queue :队列名称
         * exchange: 交换机名称
         * routingkey 路由  如果交换机类型 为 fanout  routingkey 设置未”“
         */
        channel.queueBind(queue1,exchangeName,"info");
        channel.queueBind(queue2,exchangeName,"error");

        String body="张玉敏，我爱你";


        channel.basicPublish(exchangeName,"info", null,body.getBytes());
        //channel.basicNack();  拒签
        //channel.basicAck();   签收
        channel.close();
        connection.close();
        return ApiResult.success();
    }

    /**
     * 通配符
     * @return
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    @GetMapping("routerkey")
    public ApiResult routerkey() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory=new ConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * 1.exchange  交换机名称
         * 2.type  交换机类型
         *       direct  定向
         *       fanout   广播
         *       topic  通配符方式
         *       headers  参数匹配
         * 3.durable   是否持久化
         * 4.autoDelete  自动删除
         * 5.internal   内部使用  一般false
         * 6.arguments  参数
         */

        String exchangeName="firstExchange";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,false,null);
        //创建队列
        String queue1="firstQueue";
        String queue2="secordQueue";
        channel.queueDeclare(queue1,true,false,false,null);
        channel.queueDeclare(queue2,true,false,false,null);
        /**
         * queue :队列名称
         * exchange: 交换机名称
         * routingkey 路由  如果交换机类型 为 fanout  routingkey 设置未”“
         */
        channel.queueBind(queue1,exchangeName,"info");//#.info
        channel.queueBind(queue2,exchangeName,"error");//.error.

        String body="张玉敏，我爱你";
        channel.basicPublish(exchangeName,"info",null,body.getBytes());
        channel.close();
        connection.close();
        return ApiResult.success();
    }



    @GetMapping("sendMessage")
    public ApiResult sendMessage(){
        String message ="张玉敏，我想你啊 ";
        rabbitTemplate.convertAndSend("firstExchange","boot.firstqueue",message);
        return ApiResult.success();
    }


    @GetMapping("sendMessagePubcicConfirm")
    public ApiResult sendMessagePubcicConfirm(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("confirm方法被执行了。。。。。。");
                if(b){
                    System.out.println("接收消息成功了"+s);
                }else{
                    System.out.println("接收消息失败了"+s);
                }
            }
        });

        String message ="张玉敏，我想你啊 ";
        rabbitTemplate.convertAndSend("firstExchange","boot.firstqueue",message);
        return ApiResult.success();
    }


    @GetMapping("sendMessagePubcicReturn")
    public ApiResult sendMessagePubcicReturn(){
        //设置交换机处理失败消息模式
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback(){

            @Override
            public void returnedMessage(Message message, int replyCode, String replytext,
                                        String exchange, String routingkey) {
                System.out.println("return方法被执行了。。。。。。");

//                if(b){
//                    System.out.println("接收消息成功了"+s);
//                }else{
//                    System.out.println("接收消息失败了"+s);
//                }
            }
        });
        String message ="张玉敏，我想你啊 ";
//        CorrelationData correlationData=new CorrelationData("1");
//        rabbitTemplate.convertAndSend("firstExchange","boot.firstqueue",message,correlationData);
        rabbitTemplate.convertAndSend("firstExchange","boot.firstqueue",message);
        return ApiResult.success();
    }


    @GetMapping("sendMessagePerfetch")
    public ApiResult sendMessagePerfetch(){
        String message ="张玉敏，我想你啊 ";
        //消息5秒之后不执行，自动移除队列
        MessageProperties messageProperties=new MessageProperties();
        messageProperties.setExpiration("5000");
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("firstExchange",
                    "boot.firstqueue",new Message(message.getBytes(),messageProperties));
        }

        return ApiResult.success();
    }


    @GetMapping("sendMessageTTL")
    public ApiResult sendMessageTTL(){
        String message ="张玉敏，我想你啊 ";
        //消息5秒之后不执行，自动移除队列
//        MessageProperties messageProperties=new MessageProperties();
//        messageProperties.setExpiration("5000");
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("firstExchange",
                    "boot.firstqueue",message);
        }

        return ApiResult.success();
    }
}
