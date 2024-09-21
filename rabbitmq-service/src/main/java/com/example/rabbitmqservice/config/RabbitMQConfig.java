package com.example.rabbitmqservice.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
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

    @Bean
    public Queue topicQueue(){
        return QueueBuilder.durable("topicQueue").build();
    }


    @Bean
    public Binding bingQueueExchange(@Qualifier("topicQueue") Queue queue,@Qualifier("topicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
