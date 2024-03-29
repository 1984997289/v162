package com.cm.v16productservice.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author CaoMeng
 * @Date 2019-08-06
 */
@Configuration
public class CommonConfig{
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();

        properties.setProperty("dialect","mysql");
        properties.setProperty("reasonable","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
