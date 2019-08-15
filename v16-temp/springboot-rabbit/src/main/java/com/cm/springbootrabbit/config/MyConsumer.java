package com.cm.springbootrabbit.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author CaoMeng
 * @Date 2019-08-12
 */
@Component
@RabbitListener(queues = "noExchange-queue-1")
public class MyConsumer{

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println(msg);
    }
}
