package com.cm.v16register;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V16RegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(V16RegisterApplication.class, args);
	}

}
