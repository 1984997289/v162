package com.cm.v16index;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V16IndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(V16IndexApplication.class, args);
	}

}
