package com.cm.springbootemail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEmailApplicationTests {

	@Autowired
	private JavaMailSender sender;

	@Test
	public void sendEmail(){
		//MimeBodyPart
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom("cm1984997289@163.com");
		mailMessage.setSubject("topic第一次和网易建立深度合作");         //第一次和网易建立深度合作
		mailMessage.setText("context这是合作内容");                 //这是合作内容
		mailMessage.setTo("cm1984997289@163.com");
		sender.send(mailMessage);
	}

	@Test
	public void attachFile(){
		//MimeBodyPart
		MimeMessage message=sender.createMimeMessage();

		try{
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			helper.setFrom("1984997289@qq.com");
			helper.setSubject("topic");         //第一次和网易建立深度合作
			helper.setText("context");                 //这是合作内容
			helper.setTo("cm1984997289@163.com");
			String filePath="";
			FileSystemResource fileSystemResource=new FileSystemResource(filePath);
		}catch(MessagingException e){
			e.printStackTrace();
		}
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom("cm1984997289@163.com");
		mailMessage.setSubject("topic第一次和网易建立深度合作");         //第一次和网易建立深度合作
		mailMessage.setText("context这是合作内容");                 //这是合作内容
		mailMessage.setTo("1984997289@qq.com");
		sender.send(mailMessage);

	}
}
