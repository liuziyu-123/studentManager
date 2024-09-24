package com.example.rabbitmqservice.listener;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = "queue1")
    public void queue1(Message message){
        System.out.println("消费者1的消息是"+new String(message.getBody()));
    }

    @RabbitListener(queues = "queue1")
    public void queue5(Message message){
        System.out.println("消费者2的消息是"+new String(message.getBody()));
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
}
