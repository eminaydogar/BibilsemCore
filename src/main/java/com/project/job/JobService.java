package com.project.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

import com.project.utility.HandleUtil;

@Service
public class JobService {
	private static final String SCHEDULED_TASKS = "scheduledTasks";

	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;

	@Autowired
	private Executor executor;

	public Map<String, Object> stopJob() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (!executor.isShootDown()) {
				postProcessor.postProcessBeforeDestruction(executor, SCHEDULED_TASKS);
				executor.setShootDown(true);
			} else {
				return HandleUtil.responseHandler(resultMap, "Job is already doesn't work!");
			}

		} catch (Exception e) {
			return HandleUtil.responseHandler(resultMap, "Exception during stopJob", e);
		}

		return HandleUtil.responseHandler(resultMap, "Job stopped successfully");
	}

	public Map<String, Object> startJob() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (executor.isShootDown()) {
				postProcessor.postProcessAfterInitialization(executor, SCHEDULED_TASKS);
				executor.setShootDown(false);
			} else {
				return HandleUtil.responseHandler(resultMap, "Job is already working!");
			}

		} catch (Exception e) {
			return HandleUtil.responseHandler(resultMap, "Exception during startJob", e);
		}

		return HandleUtil.responseHandler(resultMap, "Job started successfully");
	}

}
