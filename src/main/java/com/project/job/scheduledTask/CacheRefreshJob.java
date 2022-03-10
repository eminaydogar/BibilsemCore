package com.project.job.scheduledTask;

import java.util.List;

import com.project.cache.QuestionCache;
import com.project.common.annotation.JobDefinition;
import com.project.entity.QuestionDefinition;
import com.project.job.AJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@JobDefinition
public class CacheRefreshJob extends AJob {

	

	public CacheRefreshJob() {
		identifier = 3L;
	}


	@Override
	protected void execute() {
		try {
			String activeQuestionSQL = "Select * from question_definition where status='Y'";
			List<QuestionDefinition> questionDefinitionList = 
					dao.getCoreManager().getList(QuestionDefinition.class,activeQuestionSQL,null);
			if (questionDefinitionList != null && questionDefinitionList.size() > 0) {
				QuestionCache.getContext().set(questionDefinitionList);
			}
		} catch (Exception e) {
			log.error("[CacheRefreshJob]" + e.getMessage());
		}
	}



}
