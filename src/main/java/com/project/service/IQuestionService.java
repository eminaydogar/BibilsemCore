package com.project.service;

import java.util.HashMap;

import com.project.bean.QuestionBean;
import com.project.dto.QuestionDto;
import com.project.entity.BBResponse;

public interface IQuestionService {
	public BBResponse<QuestionDto> save(QuestionBean bean);
	public BBResponse<QuestionDto> update(QuestionBean bean);
	public HashMap<String, String> cacheRefresh();
	public BBResponse<QuestionDto> inactive(Long questionId);
	

}
