package com.studentManager.user.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/user/rabbit")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("work")
    public void work(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("","queue1",i+" work队列");
        }

    }

    /**
     *  发布订阅模式
     */
    @GetMapping("fanout")
    public void fanout(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("fanoutExchange","",i+" pubsub订阅模式");
        }

    }


    /**
     *  定向模式
     */
    @GetMapping("direct")
    public void direct(){
        List<String> routingkey=new LinkedList<>();
        routingkey.add("aaa");
        routingkey.add("bbb");
        routingkey.add("ccc");
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("directExchange",routingkey.get(i%3),routingkey.get(i%3));
        }

    }


    /**
     *  topic模式
     */
    @GetMapping("topic")
    public void topic(){
        List<String> routingkey=new LinkedList<>();
        routingkey.add("boot.love");
        routingkey.add("ttt.#");
        routingkey.add("wrr.love");
        routingkey.add("boot.33");
        for (int i = 0; i < 4; i++) {
            rabbitTemplate.convertAndSend("topicExchange",routingkey.get(i%4),routingkey.get(i%4));
        }
    }


}
