package com.cm.v16serach;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V16SerachApplication {

	public static void main(String[] args) {
		SpringApplication.run(V16SerachApplication.class, args);
	}

}
