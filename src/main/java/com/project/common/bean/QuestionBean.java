package com.project.common.bean;

import java.util.Date;

import com.project.cache.BBConstant.RECORD_STATUS;
import com.project.common.annotation.Mandatory;
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
	private Double yesPrice;
	@Getter
	@Setter
	@Mandatory
	private Double noPrice;
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
	private String answer;

	@Override
	public QuestionDefinition toEntity() {
		QuestionDefinition model = new QuestionDefinition();
		model.setId(questionId);
		model.setSdate(new Date());
		model.setEdate(edate);
		model.setNoPrice(noPrice);
		model.setYesPrice(yesPrice);
		model.setQuestionText(questionText);
		model.setStatus(RECORD_STATUS.OK);
		return model;
	}

}
