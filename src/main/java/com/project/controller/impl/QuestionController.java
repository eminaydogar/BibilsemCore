package com.project.controller.impl;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.QuestionBean;
import com.project.controller.IQuestionController;
import com.project.entity.BBResponse;
import com.project.service.IQuestionService;
import com.project.service.impl.QuestionService;

import io.swagger.annotations.Api;
@Api(tags = "Question Service",value = "Info")
@RestController
public class QuestionController implements IQuestionController {

	private final IQuestionService service;

	public QuestionController(QuestionService service) {
		this.service = service;
	}

	@Override
	public BBResponse<?> save(@RequestBody QuestionBean bean) {
		return service.save(bean);
	}

	@Override
	public BBResponse<?> update(@RequestBody QuestionBean bean) {
		return service.update(bean);
	}

	@Override
	public HashMap<String, String> refresh() {
		return service.cacheRefresh();
	}

	@Override
	public BBResponse<?> inactive(@PathVariable Long id) {
		return service.inactive(id);
	}

}
