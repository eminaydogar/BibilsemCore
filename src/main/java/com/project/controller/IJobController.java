package com.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.controller.url.JobURL;

@RequestMapping(value = JobURL.baseURL)
public interface IJobController {

	@PostMapping(value = JobURL.stopJob)
	public Map<String, Object> stopJob();

	@PostMapping(value = JobURL.startJob)
	public Map<String, Object> startJob();
}
