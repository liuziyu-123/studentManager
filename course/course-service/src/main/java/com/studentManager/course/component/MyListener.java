//package com.studentManager.course.component;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class MyListener {
//
//    @RabbitListener(queues="SecondQueue")
//    public void process(Message message,Channel channel) throws IOException {
//        long delivery =message.getMessageProperties().getDeliveryTag();
//        try {
//            byte[] body=message.getBody();
//            System.out.println("接收到的消息："+new String(body));
//           // channel.basicAck(delivery,true);
//        }catch (Exception e){
//            channel.basicNack(delivery,true,true);
//        }
//
//    }
//
//
//    @RabbitListener(queues="dealQueue")
//    public void dealQueue(Message message,Channel channel) throws IOException {
//        long delivery =message.getMessageProperties().getDeliveryTag();
//        try {
//            byte[] body=message.getBody();
//            System.out.println("接收到的消息："+new String(body));
//            // channel.basicAck(delivery,true);
//        }catch (Exception e){
//            channel.basicNack(delivery,true,true);
//        }
//
//    }
//
//}
