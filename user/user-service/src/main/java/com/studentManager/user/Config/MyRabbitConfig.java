package com.studentManager.user.config;


//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

//@Configuration
//public class MyRabbitConfig {
//    private static String EXCHANGE_NAME="firstExchange";
//
//    private static String DEAL_EXCHANGE_NAME="dealExchange";
//
//    private static String QUEUE_NAME="firstQueue";
//
//
//    private static String DEAL_QUEUE="dealQueue";
//
//
//    /**
//     * 声明交换机
//     */
//
//    @Bean("bootExchange")
//    public Exchange exchange(){
//
//        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
//    }
//
//
//    /**
//     * 声明交换机
//     */
//
//    @Bean("dealExchange")
//    public Exchange dealExchange(){
//
//        return ExchangeBuilder.topicExchange(DEAL_EXCHANGE_NAME).durable(true).build();
//    }
//
//
//    /**
//     * 声明队列
//     * @return
//     */
//    @Bean("bootQueue")
//    public Queue queue(){
// //       Map<String, Object> args = new HashMap<>(3);
//        //消息过期后，进入到死信交换机
////        args.put("x-dead-letter-exchange", ORDER_DEAD_LETTER_EXCHANGE);
////
////        //消息过期后，进入到死信交换机的路由key
////        args.put("x-dead-letter-routing-key", ORDER_DEAD_LETTER_QUEUE_ROUTING_KEY);
//
//        //过期时间，单位毫秒
//  //      args.put("x-message-ttl", 5000);
//
//        return QueueBuilder.durable(QUEUE_NAME).ttl(10000).maxLength(10)
//                .deadLetterExchange(DEAL_EXCHANGE_NAME).build();
//    }
//
//    /**
//     * 声明队列
//     * @return
//     */
//    @Bean("dealQueue")
//    public Queue dealQueue(){
//        //       Map<String, Object> args = new HashMap<>(3);
//        //消息过期后，进入到死信交换机
////        args.put("x-dead-letter-exchange", ORDER_DEAD_LETTER_EXCHANGE);
////
////        //消息过期后，进入到死信交换机的路由key
////        args.put("x-dead-letter-routing-key", ORDER_DEAD_LETTER_QUEUE_ROUTING_KEY);
//
//        //过期时间，单位毫秒
//        //      args.put("x-message-ttl", 5000);
//
//        return QueueBuilder.durable(DEAL_QUEUE).build();
//    }
//
//
//    @Bean
//    public Binding queueBinding(@Qualifier("bootQueue") Queue queue,@Qualifier("bootExchange") Exchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
//    }
//
//    @Bean
//    public Binding queueBindingDeal(@Qualifier("dealQueue") Queue queue,@Qualifier("dealExchange") Exchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
//    }
//
//}
