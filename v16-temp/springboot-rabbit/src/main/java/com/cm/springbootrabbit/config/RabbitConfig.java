package com.cm.springbootrabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaoMeng
 * @Date 2019-08-12
 */
@Configuration
public class RabbitConfig{

    @Bean
    public Queue getQueue(){
        return new Queue("noExchange-queue-1",false,false,false);
        
    }
}
