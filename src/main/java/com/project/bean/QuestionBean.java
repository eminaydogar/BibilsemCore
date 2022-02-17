package com.project.bean;

import java.util.Date;

import com.project.annotation.Mandatory;
import com.project.entity.QuestionDefinition;

import lombok.Getter;
import lombok.Setter;

public class QuestionBean extends BaseBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Long questionId;
	@Getter
	@Setter
	@Mandatory
	private String questionText;
	@Getter
	@Setter
	@Mandatory
	private Long yesPrice;
	@Getter
	@Setter
	@Mandatory
	private Long noPrice;
	@Getter
	@Setter
	private Date sdate;
	@Getter
	@Setter
	@Mandatory
	private Date edate;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	@Mandatory
	private String answer;

	@Override
	public QuestionDefinition toEntity() {
		QuestionDefinition model = new QuestionDefinition();
		model.setId(questionId);
		model.setAnswer(answer);
		model.setEdate(edate);
		model.setNoPrice(noPrice);
		model.setYesPrice(yesPrice);
		model.setQuestionText(questionText);
		model.setStatus("Y");
		return model;
	}

}
