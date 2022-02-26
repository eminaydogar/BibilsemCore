package com.project.controller.impl;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.bean.QuestionBean;
import com.project.controller.IQuestionController;
import com.project.entity.BBResponse;
import com.project.service.IQuestionService;
import com.project.service.impl.QuestionService;


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
	public Map<String, Object> refresh() {
		return service.cacheRefresh();
	}

	@Override
	public BBResponse<?> inactive(@PathVariable Long id) {
		return service.inactive(id);
	}

	@Override
	public BBResponse<?> getAll() {
		return service.getAll();
	}

}
