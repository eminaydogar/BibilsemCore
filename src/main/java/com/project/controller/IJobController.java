package com.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.controller.url.JobURL;

@RequestMapping(value = JobURL.baseURL)
public interface IJobController {

	@GetMapping(value = JobURL.stopJob)
	public Map<String, String> stopJob();

	@GetMapping(value = JobURL.startJob)
	public Map<String, String> startJob();
}
