package com.project.dto;

import java.io.Serializable;

import com.project.annotation.Mandatory;
import com.project.entity.QuestionAnswerDefinition;
import com.project.entity.QuestionDefinition;

import lombok.Data;

@Data
public class QuestionAnswerDto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7862529152207825837L;
	@Mandatory
	private Long id;
	@Mandatory
	private QuestionDto question;
	@Mandatory
	private String answer;

	public QuestionAnswerDto() {

	}

	public QuestionAnswerDto(QuestionAnswerDefinition questionAnswerDefinition) {
		this.id = questionAnswerDefinition.getId();
		this.question = questionDtoConverter(questionAnswerDefinition.getQuestion());
		this.answer = questionAnswerDefinition.getAnswer();
	}


	//////////////////////////// DTO Converters /////////////////////////////////

	private QuestionDto questionDtoConverter(QuestionDefinition questionDefinition) {
		if (questionDefinition != null)
			return new QuestionDto(questionDefinition);

		return new QuestionDto();
	}

}
