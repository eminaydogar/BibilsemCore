package com.project.job.scheduledTask;

import java.util.List;

import com.project.annotation.JobDefinition;
import com.project.cache.QuestionCache;
import com.project.entity.QuestionDefinition;
import com.project.job.AJob;
import com.project.job.JobDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@JobDefinition
public class CacheRefreshJob extends AJob {

	private final JobDao dao;

	public CacheRefreshJob(JobDao dao) {
		this.dao = dao;
		identifier = 3L;
	}

	@Override
	protected void initilazeBean() {
		jobBean = dao.initilazer(identifier);
	}


	@Override
	protected void execute() {
		try {
			String activeQuestionSQL = "Select * from question_definition where status='Y'";
			List<QuestionDefinition> questionDefinitionList = 
					dao.getResultList(activeQuestionSQL, QuestionDefinition.class,null);
			if (questionDefinitionList != null && questionDefinitionList.size() > 0) {
				QuestionCache.getContext().set(questionDefinitionList);
			}
		} catch (Exception e) {
			log.error("[CacheRefreshJob]" + e.getMessage());
		}
	}



}
