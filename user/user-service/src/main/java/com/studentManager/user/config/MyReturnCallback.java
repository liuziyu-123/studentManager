package com.studentManager.user.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class MyReturnCallback implements RabbitTemplate.ReturnCallback {


    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(message);
        System.out.println(i);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);

    }
}
