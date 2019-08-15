package com.cm.v16serachservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan(value = "com.cm.v16.mapper")  // @MapperScan( "com.cm.v16.mapper")
public class V16SerachServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V16SerachServiceApplication.class, args);
	}

}
