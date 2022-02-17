package com.project.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import com.project.service.IQuestionService;
import com.project.service.impl.PrizeService;
import com.project.service.impl.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CacheConfig implements InitializingBean  {

	private final IQuestionService questionService;
	private final PrizeService prizeService;
	
	public CacheConfig(QuestionService questionService,PrizeService prizeService) {
		this.questionService=questionService;
		this.prizeService=prizeService;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		initialize();
	}
	
	private void initialize() {
		initilazeQuestionCache();
		initilazePrizeCache();
	}
	
	private void initilazeQuestionCache() {
        questionService.cacheRefresh();
		log.info("-----------------Loaded QuestionCaches successfully ...");
	}
	
	private void initilazePrizeCache() {
		 prizeService.cacheRefresh();
		log.info("--------------------Loaded initilazePrizeCache successfully ...");
	}


}
