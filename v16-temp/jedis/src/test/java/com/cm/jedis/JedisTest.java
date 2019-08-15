package com.cm.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CaoMeng
 * @Date 2019-08-14
 */
public class JedisTest{

    @Test
    public void stringTest(){
        Jedis jedis=new Jedis("114.55.146.162",6379);
        
        jedis.auth("caomeng");
        jedis.set("name","caomeng");
        String name=jedis.get("name");
        System.out.println(name);
        Map<String,String> map=new HashMap<String,String>();
        map.put("name","围城");
        map.put("author","钱钟书");
        jedis.hmset("book:1", map);
        List<String> hmget=jedis.hmget("book:1","name","author");
        for(String s : hmget){
            System.out.println(s);
        }
    }

    @Test
    public void listTest(){
        Jedis jedis=new Jedis("114.55.146.162",6379);

        jedis.auth("caomeng");
        jedis.set("name","caomeng");
        String name=jedis.get("name");
        System.out.println(name);
       
        jedis.lpush("list1", "围城","钱钟书","撒哈拉的沙漠","三毛");
        List<String> list1=jedis.lrange("list1", 0, -1);
        for(String s : list1){
            System.out.println(s);
        }

    }
}
