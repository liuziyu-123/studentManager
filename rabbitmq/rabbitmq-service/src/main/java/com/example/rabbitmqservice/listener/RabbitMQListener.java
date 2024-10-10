package com.example.rabbitmqservice.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = "queue1",ackMode = "MANUAL")
    public void queue1(Message message, Channel channel)  {
        try {
            //System.out.println("收到订单消息：" + order);
            // 处理订单逻辑
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认消息
            System.out.println("消费者1的消息是"+new String(message.getBody()));
        } catch (Exception e) {
          //  System.out.println("处理消息失败：" + order + "，原因：" + e.getMessage());
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // 拒绝消息，并重新放入队列
            } catch (IOException ex) {
                // 处理异常
            }
        }


    }

    @RabbitListener(queues = "queue1")
    public void queue5(Message message, Channel channel){
        try {
            //System.out.println("收到订单消息：" + order);
            // 处理订单逻辑
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认消息
            System.out.println("消费者2的消息是"+new String(message.getBody()));
        } catch (Exception e) {
            //  System.out.println("处理消息失败：" + order + "，原因：" + e.getMessage());
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // 拒绝消息，并重新放入队列
            } catch (IOException ex) {
                // 处理异常
            }
        }
    }

    @RabbitListener(queues = "queue2")
    public void queue2(Message message){
        System.out.println("queue2消息是"+new String(message.getBody()));
    }

    @RabbitListener(queues = "queue3")
    public void queue3(Message message){
        System.out.println("queue3消息是"+new String(message.getBody()));
    }

    @RabbitListener(queues = "queue4")
    public void queue4(Message message){
        System.out.println("queue4消息是"+new String(message.getBody()));
    }

//    @RabbitListener(queues = "queue5")
//    public void queue5(Message message){
//        System.out.println("queue5消息是"+new String(message.getBody()));
//    }


    @RabbitListener(queues = "queueDead")
    public void queueDead(Message message){
        System.out.println("queueDead的消息"+new String(message.getBody()));
    }

    @RabbitListener(queues = "queue6",ackMode = "MANUAL")
    public void queue6(Message message,Channel channel){
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            Thread.sleep(2000);
            System.out.println("queue6消息是"+new String(message.getBody()));
            //int a = 1/0;
            //手动ack  第二个参数为false表示仅确认当前消息，为true表示确认之前所有的消息
            channel.basicAck(deliveryTag, false);
        }catch (Exception e) {
            //手动nack 告诉rabbitmq该消息消费失败  第三个参数：如果被拒绝的消息应该被重新请求，而不是被丢弃或变成死信，则为true
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }



    @RabbitListener(queues = "simpleQueue", ackMode = "MANUAL",containerFactory = "consumer2ContainerFactory")
    @RabbitHandler
    public void receiveMessageFromSimpleQueue(Object message, Channel channel, Message amqpMessage) throws IOException {
        try {
            // 将消息转换为字符串
            byte[] body = amqpMessage.getBody();
            // 将消息转换为字符串
            String msg = new String(body, StandardCharsets.UTF_8);
            Thread.sleep(1000);
            // 判断条件，假设不满足的条件为 msg.equals("bad")
            if (!"bad".equals(msg)) {
                // 如果消息不符合条件，拒绝并将其放入死信队列
                channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
                return;
            }

            // 正常处理消息
            System.out.println("Received message from simpleQueue1: " + msg);
           // channel.basicQos(4);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 发生异常时，将消息转移到死信队列
            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
            // 如果处理失败，则拒绝消息，第三个参数true表示是否重新入队
            // channel.basicNack(deliveryTag, false, true);
            // 处理失败，拒绝消息，不会重新入队，将进入死信队列
            // channel.basicReject(deliveryTag, false);
        }
    }

    @RabbitListener(queues = "simpleQueue", ackMode = "MANUAL",containerFactory = "consumer1ContainerFactory")
    @RabbitHandler
    public void receiveMessageFromSimpleQueue(String message, Channel channel, Message amqpMessage) throws IOException {
        try {
            Thread.sleep(6000);
            // 判断条件，假设不满足的条件为 message.equals("bad")
            if (!"bad".equals(message)) {
                // 如果消息不符合条件，拒绝并将其放入死信队列
                channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
                return;
            }

            // 正常处理消息
            System.out.println("Received message from simpleQueue2: " + message);
            //channel.basicQos(2);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 发生异常时，将消息转移到死信队列
            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
            // 如果处理失败，则拒绝消息，第三个参数true表示是否重新入队
            // channel.basicNack(deliveryTag, false, true);
            // 处理失败，拒绝消息，不会重新入队，将进入死信队列
            // channel.basicReject(deliveryTag, false);
        }
    }

    @RabbitListener(queues = "directQueue", ackMode = "MANUAL")
    public void receiveMessageFromDirectQueue(Object message, Channel channel, Message amqpMessage) throws IOException {
        try {
            // 将消息转换为字符串
            byte[] body = amqpMessage.getBody();
            // 将消息转换为字符串
            String msg = new String(body, StandardCharsets.UTF_8);
            Thread.sleep(6000);
            System.out.println("Received message from directQueue: " + msg);
            channel.basicQos(1);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
        }
    }


    @RabbitListener(queues = "directQueue", ackMode = "MANUAL")
    public void receiveMessageFromDirectQueue(String message, Channel channel, Message amqpMessage) throws IOException {
        try {
            Thread.sleep(6000);
            System.out.println("Received message from directQueue: " + message);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "topicQueue")
    public void receiveMessageFromTopicQueue(Object message, Channel channel, Message amqpMessage) throws IOException {
        try {
            byte[] body = amqpMessage.getBody();
            // 将消息转换为字符串
            String msg = new String(body, StandardCharsets.UTF_8);
            Thread.sleep(6000);
            System.out.println("Received message from topicQueue: " + msg);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "topicQueue")
    public void receiveMessageFromTopicQueue(String message, Channel channel, Message amqpMessage) throws IOException {
        try {
            Thread.sleep(6000);
            System.out.println("Received message from topicQueue: " + message);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "fanoutQueue")
    public void receiveMessageFromFanoutQueue(Object message, Channel channel, Message amqpMessage) throws IOException {
        try {
            // 将消息转换为字符串
            byte[] body = amqpMessage.getBody();
            // 将消息转换为字符串
            String msg = new String(body, StandardCharsets.UTF_8);
            System.out.println("Received message from fanoutQueue: " + msg);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
        }
    }







     @RabbitListener(queues = "fanoutQueue")
     public void receiveMessageFromFanoutQueue(String message, Channel channel, Message amqpMessage) throws IOException {
         try {
             Thread.sleep(6000);
             System.out.println("Received message from fanoutQueue: " + message);
             channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
         } catch (Exception e) {
             channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
         }
     }

     @RabbitListener(queues = "dlq")
     public void receiveMessageFromDLQ(String message,Channel channel, Message amqpMessage) {
        try {
            Thread.sleep(6000);
         System.out.println("=========Received message from DLQ: " + message);
         // 这里可以执行一些处理逻辑，例如记录日志或者通知运维人员
         channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
     } catch (Exception e) {

    }
     }


}
