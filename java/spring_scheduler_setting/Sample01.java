package com.chunlab.app.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableAsync
@EnableScheduling
@Configuration
public class Sample01 {

	
	// 5초마다 수행됨
	@Async
	@Scheduled( fixedRate = 5000)
	public void sendMail() {
		
		System.out.println("Test");
	}
}
