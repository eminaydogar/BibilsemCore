package com.project.job.beans;

import java.math.BigInteger;

import lombok.Data;

@Data
public class CouponValidatiorBean {
	
	private Long couponId;
	private Long questionId;
	private String coupon_answer;
	private String question_text;
	private String question_answer;
	private CouponValidatiorBean next;
	
	public CouponValidatiorBean(Object[] rows){
		couponId = ((BigInteger) rows[0]).longValue();
		questionId = ((BigInteger) rows[1]).longValue();
		coupon_answer = rows[2].toString();
		question_text = rows[3].toString();
		question_answer=rows[4].toString();
	}
	

}
