package com.studentManager.user.config;

//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

//public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//
//    @PostConstruct
//    public void  init(){
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }
//    /**
//     * 交换机确认回调方法
//     * 1.发消息  交换机接收到了  回调
//     *
//     * @param correlationData   保存回调消息的ID及相关信息
//     * @param b   ack
//     * @param s  ack为false  失败的原因
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        if(b){
//            System.out.println("交换机已经收到了"+correlationData.getId()+"消息");
//        }else{
//            System.out.println("失败的原因"+s);
//        }
//
//    }
//
//    /**
//     * 可以在当消息传递过程中不可达目的地时将消息返回给生产者
//     * @param message
//     * @param i
//     * @param s
//     * @param s1
//     * @param s2
//     */
//    @Override
//    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
//
//    }
//}
