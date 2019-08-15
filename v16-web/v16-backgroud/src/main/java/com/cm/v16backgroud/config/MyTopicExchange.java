package com.cm.v16backgroud.config;

import com.cm.v16.common.constant.RabbitTopicExchange;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author CaoMeng
 * @Date 2019-08-12
 */
@Configuration
public class MyTopicExchange{

    /*
    * TODO 声明队列+交换机+绑定两者
    * */
    @Bean
    public Queue getQueue1(){
        return new Queue("queue-1",false,false,false,null);
    }

    @Bean
    public Queue getQueue2(){
        return new Queue("queue-2",false,false,false,null);
    }
    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange(RabbitTopicExchange.BACKGROUD_TOPICEXCHANGE,true,false);
    }

    @Bean
    public Binding bindingEx_Qu1(Queue getQueue1, TopicExchange exchange){
        return BindingBuilder.bind(getQueue1).to(exchange).with("product.*");
    }

    @Bean
    public Binding bindingEx_Qu2(Queue getQueue2, TopicExchange exchange){
        return BindingBuilder.bind(getQueue2).to(exchange).with("product.add");
    }
}
