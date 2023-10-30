package com.studentManager.course.config;


//@Configuration
//public class MyRabbitConfig {
//    private static String EXCHANGE_NAME="firstExchange";
//
//    private static String QUEUE_NAME="firstQueue";
//
//
//    /**
//     * 声明交换机
//     */
//
//    @Bean
//    public FanoutExchange exchange(){
//            return new FanoutExchange(EXCHANGE_NAME,true,true);
//    }
//
//
//    /**
//     * 声明队列
//     * @return
//     */
//    @Bean
//        public Queue queue(){
//        return new Queue(QUEUE_NAME,true,false,false);
//    }
//
//
//    @Bean
//    public Binding queueBinding(Queue queue,FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(queue).to(fanoutExchange);
//    }
//
//}
