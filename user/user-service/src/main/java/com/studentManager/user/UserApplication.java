package com.studentManager.user;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/11/27
 **/
@SpringBootApplication
@EnableRabbit
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
