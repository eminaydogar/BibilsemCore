package com.project.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.QuestionBean;
import com.project.controller.url.QuestionURL;
import com.project.entity.BBResponse;
@RequestMapping(QuestionURL.basePath)
public interface IQuestionController {

	@PostMapping(value = QuestionURL.save)
	BBResponse<?> save(@RequestBody QuestionBean bean);
	
	@PostMapping(value = QuestionURL.update)
	BBResponse<?> update(@RequestBody QuestionBean bean);
	
	@GetMapping(value = QuestionURL.refresh)
	HashMap<String, String> refresh();
	
	@PostMapping(value = QuestionURL.inactive)
	BBResponse<?> inactive(@PathVariable Long id);
}
