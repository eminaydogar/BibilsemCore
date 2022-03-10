package com.project.queue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MessageExecutor {

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	
	
	@PostConstruct
	private void init() {
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setQueueCapacity(10);
	}
	
}
