package com.cm.springbootrabbit.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author CaoMeng
 * @Date 2019-08-12
 */
@Component
public class Sender{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg){
//        System.out.println("Sender send a msg ...");
        //发送消息到队列
        rabbitTemplate.convertAndSend("noExchange-queue-1",msg);
    }
}
