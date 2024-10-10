package com.studentManager.user.config;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@Component
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setConfirmCallback(this);
//    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (correlationData != null) {
            String correlationId = correlationData.getId();
            if (correlationId.startsWith("type1_")) {
                // 处理类型1的消息确认逻辑
                handleType1Confirm(correlationData, ack, cause);
            } else if (correlationId.startsWith("type2_")) {
                // 处理类型2的消息确认逻辑
                handleType2Confirm(correlationData, ack, cause);
            } else {
                // 处理其他类型的消息确认逻辑
                handleDefaultConfirm(correlationData, ack, cause);
            }
        }
    }

    private void handleType1Confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 类型1的确认逻辑
        if(ack) {
            System.out.println(correlationData.getId());
            System.out.println("交换机成功接受到了消息");
        } else {
            System.out.println("消息失败原因" + cause);
        }

    }

    private void handleType2Confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 类型2的确认逻辑
    }

    private void handleDefaultConfirm(CorrelationData correlationData, boolean ack, String cause) {
        // 默认的确认逻辑
    }
}
