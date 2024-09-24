package com.studentManager.user.Config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean()
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange("topicExchange").durable(true).build();
    }
    @Bean
    public Exchange directExchange(){
        return ExchangeBuilder.directExchange("directExchange").durable(true).build();
    }

    @Bean
    public Exchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange("fanoutExchange").durable(true).build();
    }

    @Bean
    public Exchange headersExchange(){
        return ExchangeBuilder.headersExchange("headersExchange").durable(true).build();
    }

    @Bean()
    public Queue queue1(){
        return QueueBuilder.durable("queue1").build();
    }


    @Bean()
    public Queue queue2(){
        return QueueBuilder.durable("queue2").build();
    }
    @Bean()
    public Queue queue3(){
        return QueueBuilder.durable("queue3").build();
    }
    @Bean()
    public Queue queue4(){
        return QueueBuilder.durable("queue4").build();
    }


    @Bean
    public Binding bingQueue1Exchange(@Qualifier("queue1") Queue queue,@Qualifier("topicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

    @Bean
    public Binding bingQueue2Exchange(@Qualifier("queue2") Queue queue,@Qualifier("topicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("#.love").noargs();
    }


    @Bean
    public Binding bingQueue6Exchange(@Qualifier("queue2") Queue queue,@Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("ccc").noargs();
    }

    @Bean
    public Binding bingQueue7Exchange(@Qualifier("queue3") Queue queue,@Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("aaa").noargs();
    }

    @Bean
    public Binding bingQueue3Exchange(@Qualifier("queue3") Queue queue,@Qualifier("directExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("bbb").noargs();
    }


    @Bean
    public Binding bingQueue4Exchange(@Qualifier("queue3") Queue queue,@Qualifier("fanoutExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding bingQueue5Exchange(@Qualifier("queue4") Queue queue,@Qualifier("fanoutExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}
