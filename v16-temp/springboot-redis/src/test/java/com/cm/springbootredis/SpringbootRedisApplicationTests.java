package com.cm.springbootredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

	@Resource(name = "stringRsdisModue2")
	private RedisTemplate<String,Object> redisTemplate;

	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("name","赵丽");
		Object name=redisTemplate.opsForValue().get("name");
		System.out.println(name);
	}

}
