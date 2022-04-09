package com.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.common.bean.QuestionBean;
import com.project.controller.url.QuestionURL;
import com.project.entity.BBResponse;

import io.swagger.annotations.Api;

@Api(tags = "Question Service", value = "Info")
@RequestMapping(QuestionURL.basePath)
public interface IQuestionController {

	@GetMapping(value = QuestionURL.getAll)
	BBResponse<?> getAll();

	@PostMapping(value = QuestionURL.save)
	BBResponse<?> save(@RequestBody QuestionBean bean);

	@PostMapping(value = QuestionURL.update)
	BBResponse<?> update(@RequestBody QuestionBean bean);

	@GetMapping(value = QuestionURL.refresh)
	Map<String, Object> refresh();

	@PostMapping(value = QuestionURL.inactive)
	BBResponse<?> inactive(@PathVariable Long id);

	@GetMapping(value = QuestionURL.getLatest)
	BBResponse<?> getLatest();
}
