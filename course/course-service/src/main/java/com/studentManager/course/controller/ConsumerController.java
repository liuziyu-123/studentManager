package com.studentManager.course.controller;

import com.rabbitmq.client.*;
import com.studentManager.common.Utils.ApiResult;
import com.studentManager.course.service.ServiceUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private ServiceUserFeign serviceUserFeign;

    @GetMapping("getMessage")
    public ApiResult getMessage() throws IOException, TimeoutException {

        ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost();
//        factory.setPort();
//        factory.setVirtualHost();
//        factory.setUsername();
//        factory.setPassword();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * queue :队列名称
         * durable :是否持久化  当mq 重新启动之后，还在
         * exclusive:是否独立。只能用一个消费这监听这队列  当Connection 关闭时，是否删除队列
         * autoDelete :是否自动删除。当没有 Consume 时，自动删除掉
         * arguments :参数
         */

      //  channel.queueDeclare("hello",true,false,false,null);
        /**
         * 1.queue :队列名称
         * 2.autoAck :是否自动确认
         * 3.callback:回调对象
         */

        Consumer consumer =new DefaultConsumer(channel){

            /**
             *
             * @param consumerTag  标识
             * @param envelope 获取一些信息  交换机   路由key
             * @param properties  配置信息
             * @param body  数据
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("consumerTag:"+consumerTag);
//                System.out.println("Exchange: "+envelope.getExchange());
//                System.out.println("routingkey"+envelope.getRoutingKey());
//                System.out.println("properties: "+properties);
                System.out.println("body: "+new String(body));

            }
        };
        channel.basicQos(1);//  0 轮流分发，  1 不公平分发
        channel.basicConsume("hello",true,consumer);



        return ApiResult.success();
    }

    @GetMapping("getWorkQueue")
    public ApiResult getWorkQueue() throws IOException, TimeoutException {

        ConnectionFactory factory=new ConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

     //   channel.queueDeclare("hello",true,false,false,null);

        Consumer consumer =new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:"+consumerTag);
                System.out.println("body: "+new String(body));

            }
        };


        channel.basicConsume("work_queues",true,consumer);

        return ApiResult.success();
    }


    @GetMapping("getPubsub1")
    public ApiResult getPubsub1() throws IOException, TimeoutException {

        ConnectionFactory factory=new ConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //channel.queueDeclare("queue1",true,false,false,null);
        String queue1="firstQueue";

        Consumer consumer =new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:"+consumerTag);
                System.out.println("body: "+new String(body));
                System.out.println("打印到控制台");

            }
        };
          //接收消息
//        DeliverCallback deliverCallback=(consumerTag,message)->{
//            System.out.println(message);
//        };
        //取消消息时的回调
//        CancelCallback cancelCallback=consumerTag->{
//            System.out.println("消息消费被中断了");
//        };

        channel.basicConsume(queue1,true,consumer);
//        channel.basicConsume(queue1,true,deliveryCallback,cancelCallback);

        return ApiResult.success();
    }


    @GetMapping("getPubsub2")
    public ApiResult getPubsub2() throws IOException, TimeoutException {

        ConnectionFactory factory=new ConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //channel.queueDeclare("hello",true,false,false,null);
        String queue2="secordQueue";
        Consumer consumer =new DefaultConsumer(channel){


            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:"+consumerTag);
                System.out.println("body: "+new String(body));
                System.out.println("保存到数据库");
            }
        };
        channel.basicConsume(queue2,true,consumer);



        return ApiResult.success();
    }

    @GetMapping("getTest")
    public ApiResult getTest() throws IOException, TimeoutException {

        return ApiResult.success(serviceUserFeign.test());
    }

}
