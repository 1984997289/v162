package com.cm.springbootsolr;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.cm.v16.mapper")
public class SpringbootSolrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSolrApplication.class, args);
	}
}
