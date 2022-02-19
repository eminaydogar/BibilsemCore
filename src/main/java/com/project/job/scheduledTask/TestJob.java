package com.project.job.scheduledTask;

import java.util.Date;

import com.project.annotation.JobDefinition;
import com.project.job.AJob;
import com.project.job.JobDao;


@JobDefinition
public class TestJob extends AJob {

	private final JobDao dao;

	public TestJob(JobDao dao) {
		this.dao = dao;
		identifier = 2L;
		initilazeBean();
	}
	
	@Override
	protected void initilazeBean() {
		jobBean = dao.initilazer(identifier);

	}

	@Override
	protected void execute() {
		System.out.println("TestJob JOB CALISIYOR!!!!!!!!\" CALISMA ZAMANI [" + new Date() + "]");
	}


}
