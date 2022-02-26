package com.project.controller.impl;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.project.controller.IJobController;
import com.project.job.JobService;

@RestController
public class JobController implements IJobController {

	private final JobService service;

	public JobController(JobService service) {
		this.service = service;
	}

	@Override
	public Map<String, Object> stopJob() {
		return service.stopJob();
	}

	@Override
	public Map<String, Object> startJob() {
		return service.startJob();
	}

}
