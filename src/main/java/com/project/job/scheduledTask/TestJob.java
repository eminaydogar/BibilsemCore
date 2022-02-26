package com.project.job.scheduledTask;

import java.util.Date;

import com.project.common.annotation.JobDefinition;
import com.project.job.AJob;

@JobDefinition
public class TestJob extends AJob {

	public TestJob() {

		identifier = 2L;
	}

	@Override
	protected void execute() {
		System.out.println("TestJob JOB CALISIYOR!!!!!!!!\" CALISMA ZAMANI [" + new Date() + "]");
	}

}
