package com.cm.springbootrabbit;

import com.cm.springbootrabbit.config.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void contextLoads() {
		sender.sendMsg("Sender send a msg ...");
	}

}
