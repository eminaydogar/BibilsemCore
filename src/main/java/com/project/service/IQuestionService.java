package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.common.bean.QuestionBean;
import com.project.common.dto.QuestionDto;
import com.project.entity.BBResponse;

public interface IQuestionService {
	public BBResponse<List<QuestionDto>> getAll();
	public BBResponse<QuestionDto> save(QuestionBean bean);
	public BBResponse<QuestionDto> update(QuestionBean bean);
	public Map<String, Object> cacheRefresh();
	public BBResponse<QuestionDto> inactive(Long questionId);
	

}
