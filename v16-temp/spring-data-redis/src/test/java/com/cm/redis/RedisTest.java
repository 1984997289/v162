package com.cm.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author CaoMeng
 * @Date 2019-08-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest{
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void stringTest(){
        redisTemplate.opsForValue().set("target","nba");
        Object target = redisTemplate.opsForValue().get("target");
        System.out.println(target);
    }
}
