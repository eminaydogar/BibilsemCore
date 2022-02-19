package com.project.job.beans;

import java.math.BigInteger;

import lombok.Data;

@Data
public class CouponValidatiorBean {

	private Long couponId;
	private Long questionId;
	private String coupon_answer;
	private String question_answer;
	private Long userId;
	private Long couponPrize;
	private CouponValidatiorBean next;

	public CouponValidatiorBean(Object[] rows) {
		couponId = ((BigInteger) rows[0]).longValue();
		questionId = ((BigInteger) rows[1]).longValue();
		coupon_answer = rows[2].toString();
		question_answer = rows[3].toString();
		userId = ((BigInteger) rows[4]).longValue();
		couponPrize = ((BigInteger) rows[5]).longValue();
	}

}
