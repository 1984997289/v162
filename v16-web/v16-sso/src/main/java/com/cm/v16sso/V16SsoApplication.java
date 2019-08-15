package com.cm.v16sso;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V16SsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(V16SsoApplication.class, args);
	}

}
