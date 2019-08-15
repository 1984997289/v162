package com.cm.v16productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author CaoMeng
 * @Date 2019-08-14
 */
@Configuration
public class RedisConfig{

    @Bean(name = "stringRsdisModuel")
    public RedisTemplate<String,Object> redisTemplate1(RedisConnectionFactory factory){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean(name = "stringRsdisModue2")
    public RedisTemplate<String,String> redisTemplate2(RedisConnectionFactory factory){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
