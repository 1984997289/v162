package com.cm.v16item;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V16ItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(V16ItemApplication.class, args);
	}

}
