package com.project.cache;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.project.common.service.CoreContainerService;
import com.project.entity.PrizeDefinition;
import com.project.entity.QuestionDefinition;
import com.project.utility.SecureUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CacheConfig implements InitializingBean  {
	
	@Autowired
	private CoreContainerService coreContainerService;

	@Override
	public void afterPropertiesSet() throws Exception {
		initialize();
		SecureUtility.init();
	}
	
	private void initialize() {
		initilazeQuestionCache();
		initilazePrizeCache();
	}
	
	private void initilazeQuestionCache() {
		
		List<QuestionDefinition> questionDefinitionList = coreContainerService.getCoreManager().getList(QuestionDefinition.class, "SELECT * FROM QUESTION_DEFINITION WHERE STATUS=?", "Y");
		QuestionCache.getContext().set(questionDefinitionList);
		log.info("-----------------Loaded QuestionCaches successfully ...");
	}
	
	private void initilazePrizeCache() {
		List<PrizeDefinition> prizeDefinitionList = coreContainerService.getCoreManager().getList(PrizeDefinition.class, "SELECT * FROM PRIZE_DEFINITION WHERE STATUS=?", "Y");
		PrizeCache.getContext().set(prizeDefinitionList);
		log.info("--------------------Loaded initilazePrizeCache successfully ...");
	}


}
