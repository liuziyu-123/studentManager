package com.example.rabbitmqservice.controller;

import com.studentManager.common.Utils.ApiResult;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("rabbit")
public class TestController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @GetMapping("topicExchange")
//    public void test(){
//        rabbitTemplate.convertAndSend("topicExchange","boot","张玉敏，昨晚我梦到你了");
//    }

}
