package com.project.common.bean;

import com.project.common.annotation.Mandatory;
import com.project.cache.QuestionCache;
import com.project.entity.QuestionAnswerDefinition;
import com.project.entity.QuestionDefinition;
import com.project.exception.EntityNotFoundException;

import lombok.Getter;

public class AnswerBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3508645766614024055L;

	@Getter
	@Mandatory
	private Long questionId;

	@Getter
	@Mandatory
	private String answer;

	@Override
	public QuestionAnswerDefinition toEntity() throws EntityNotFoundException {
		QuestionAnswerDefinition model = new QuestionAnswerDefinition();
		QuestionDefinition question = QuestionCache.getContext().getById(questionId);
		if (question == null) {
			throw new EntityNotFoundException("Nonexistent question value");
		}
		model.setQuestion(question);
		model.setAnswer(answer);
		return model;

	}

}
