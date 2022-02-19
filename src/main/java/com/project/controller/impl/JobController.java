package com.project.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.RestController;

import com.project.controller.IJobController;
import com.project.job.Executor;
import com.project.utility.HandleUtil;

@RestController
public class JobController implements IJobController {
	private static final String SCHEDULED_TASKS = "scheduledTasks";

	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;

	@Autowired
	private Executor executor;

	@Override
	public Map<String, String> stopJob() {
		try {
			if(!executor.isShootDown()) {
				postProcessor.postProcessBeforeDestruction(executor, SCHEDULED_TASKS);
				executor.setShootDown(true);
			}else {
				return HandleUtil.responseHandler("Job is already doesn't work!");
			}
		
		} catch (Exception e) {
			return HandleUtil.responseHandler("Exception during stopJob", e);
		}

		return HandleUtil.responseHandler("Job stopped successfully");
	}

	@Override
	public Map<String, String> startJob() {
		try {
			if(executor.isShootDown()) {
				postProcessor.postProcessAfterInitialization(executor, SCHEDULED_TASKS);
				executor.setShootDown(false);
			}else {
				return HandleUtil.responseHandler("Job is already working!");
			}
			
		} catch (Exception e) {
			return HandleUtil.responseHandler("Exception during startJob", e);
		}

		return HandleUtil.responseHandler("Job started successfully");
	}

}
