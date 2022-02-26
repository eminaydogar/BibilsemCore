package com.project.common.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.project.common.annotation.Mandatory;
import com.project.entity.CouponDefinition;
import com.project.entity.QuestionAnswerDefinition;
import com.project.exception.EntityNotFoundException;

import lombok.Getter;
import lombok.Setter;

public class UserCouponBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7167041828454892674L;

	@Getter
	@Setter
	@Mandatory
	private Long userId;

	@Getter
	@Setter
	private Long couponId;

	@Getter
	@Setter
	private String couponName;

	@Getter
	@Setter
	@Mandatory
	private Long couponAmount;
	
	@Getter
	@Setter
	@Mandatory
	private Long couponPrice;

	@Getter
	@Setter
	private Date edate;

	@Getter
	@Setter
	private Date sdate;

	@Getter
	@Setter
	private String couponStatus;

	@Getter
	@Setter
	@Mandatory
	private Set<AnswerBean> answers;

	@Override
	public CouponDefinition toEntity() throws EntityNotFoundException {
		CouponDefinition model = new CouponDefinition();
		model.setName(couponName);
		model.setAmount(couponAmount);
		model.setPrice(couponPrice);
		model.setEdate(null);
		model.setSdate(new Date());
		model.setStatus("W");
		model.setDetails(setAnswerBeans(answers));
		return model;
	}
	
	protected Set<QuestionAnswerDefinition> setAnswerBeans(Set<AnswerBean> beanList) throws EntityNotFoundException{
		Set<QuestionAnswerDefinition> answers = new HashSet<QuestionAnswerDefinition>();
		for(AnswerBean answer:beanList) {
			answers.add(answer.toEntity());
		}
		return answers;
	}

}
