package com.cm.springbootfastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FdfsClientConfig.class)    //注入Fdfs配置信息
public class SpringbootFastdfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFastdfsApplication.class, args);
	}

}
