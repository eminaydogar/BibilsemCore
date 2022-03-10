package com.project.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.project.entity.QuestionDefinition;

import lombok.Data;

@Data
public class QuestionDto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4340091939071737634L;

	private Long id;

	private String questionText;

	private Double yesPrice;

	private Double noPrice;

	private Date sdate;

	private Date edate;

	private String status;

	private String answer;

	public QuestionDto() {
	}

	public QuestionDto(QuestionDefinition questionDefinition) {
		this.id = questionDefinition.getId();
		this.questionText = questionDefinition.getQuestionText();
		this.yesPrice = questionDefinition.getYesPrice();
		this.noPrice = questionDefinition.getNoPrice();
		this.sdate = questionDefinition.getSdate();
		this.edate = questionDefinition.getEdate();
		this.status = questionDefinition.getStatus();
		this.answer=questionDefinition.getAnswer();
	}



}
