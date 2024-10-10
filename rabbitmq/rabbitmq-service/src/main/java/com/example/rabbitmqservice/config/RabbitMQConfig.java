package com.example.rabbitmqservice.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String TOPIC_EXCHANGE = "topicExchange";

    public static final String SIMPLE_QUEUE = "simpleQueue";
    public static final String FANOUT_QUEUE = "fanoutQueue";
    public static final String DIRECT_QUEUE = "directQueue";
    public static final String TOPIC_QUEUE = "topicQueue";

    public static final String DIRECT_ROUTING_KEY = "directRoutingKey";
    public static final String TOPIC_ROUTING_KEY = "topicRoutingKey";

    public static final String DLX_EXCHANGE = "dlxExchange";
    public static final String DLQ = "dlq";

    //这个可以设置预取值
    @Bean
    SimpleRabbitListenerContainerFactory consumer1ContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(2); // 设置消费者1的预取值为1
        return factory;
    }

    @Bean
    SimpleRabbitListenerContainerFactory consumer2ContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(4); // 设置消费者2的预取值为5
        return factory;
    }

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
    public Exchange ttlExchange(){
        return ExchangeBuilder.topicExchange("ttlExchange").durable(true).build();
    }

    @Bean
    public Exchange headersExchange(){
        return ExchangeBuilder.headersExchange("headersExchange").durable(true).build();
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public Exchange deadExchange(){
        return ExchangeBuilder.topicExchange("deadExchange").durable(true).build();
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
    public Queue queue6(){
        return QueueBuilder.durable("queue6")
                .withArgument("x-message-ttl",3000)
                .withArgument("x-max-length",5)
                .withArgument("x-dead-letter-exchange","deadExchange")
                .withArgument("x-dead-letter-routing-key","dead").build();
    }


    /**
     * 死信队列
     * @return
     */
    @Bean()
    public Queue queueDead(){
        return QueueBuilder.durable("queueDead").build();
    }


    @Bean()
    public Queue queue5(){
        return QueueBuilder.durable("queue5").build();
    }
//    @Bean()
//    public Queue queue6(){
//        return QueueBuilder.durable("queue6").build();
//    }


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
    public Binding bingQueue5Exchange(@Qualifier("queue6") Queue queue,@Qualifier("fanoutExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }


    @Bean
    public Binding bingQueue8Exchange(@Qualifier("queue6") Queue queue,@Qualifier("ttlExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }


    @Bean
    public Binding bingQueue9Exchange(@Qualifier("queueDead") Queue queue,@Qualifier("deadExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dead").noargs();
    }




    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    @Bean
    public Queue dlq() {
        return new Queue(DLQ, true);
    }

    @Bean
    public Binding bindingDLQToDLX() {
        return BindingBuilder.bind(dlq()).to(dlxExchange()).with(DLQ);
    }

    @Bean
    public Queue simpleQueue() {
        // 使用QueueBuilder构建队列，durable就是持久化的
        return QueueBuilder.durable(SIMPLE_QUEUE)
                .ttl(3000) // 设置队列的 TTL，单位：毫秒
                .deadLetterExchange(DLX_EXCHANGE) // 绑定死信交换机
                .deadLetterRoutingKey(DLQ) // 绑定死信队列
                .build();
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE, true);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE, true);
    }

    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE, true);
    }

    @Bean
    public FanoutExchange fanoutExchange1() {
        // 交换机名称，是否持久化，是否自动删除
        return new FanoutExchange(FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange directExchange2() {
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public TopicExchange topicExchange3() {
        return new TopicExchange(TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingFanoutQueueToFanoutExchange() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange1());
    }

    @Bean
    public Binding bindingDirectQueueToDirectExchange() {
        return BindingBuilder.bind(directQueue()).to(directExchange2()).with(DIRECT_ROUTING_KEY);
    }

    @Bean
    public Binding bindingTopicQueueToTopicExchange() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange3()).with(TOPIC_ROUTING_KEY);
    }
}
