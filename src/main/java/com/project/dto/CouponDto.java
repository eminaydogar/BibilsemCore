package com.project.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.project.entity.CouponDefinition;
import com.project.entity.QuestionAnswerDefinition;

import lombok.Data;

@Data
public class CouponDto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1228052495700130068L;
	private Long id;
	private String name;
	private Date sdate;
	private Date edate;
	private String status;
	private Long price;
	private Long amount;
	
	
	private Set<QuestionAnswerDto> details;

	public CouponDto() {

	}

	public CouponDto(CouponDefinition coupon) {
		this.id = coupon.getId();
		this.name = coupon.getName();
		this.sdate = coupon.getSdate();
		this.edate = coupon.getEdate();
		this.status = coupon.getStatus();
		this.price = coupon.getPrice();
		this.amount = coupon.getAmount();
		this.details = detailsDtoConverter(coupon.getDetails());

	}

	public CouponDto(CouponDefinition coupon, boolean fetchDetails) {
		this.id = coupon.getId();
		this.name = coupon.getName();
		this.sdate = coupon.getSdate();
		this.edate = coupon.getEdate();
		this.status = coupon.getStatus();
		this.price = coupon.getPrice();
		this.amount = coupon.getAmount();
		if (fetchDetails)
			this.details = detailsDtoConverter(coupon.getDetails());

	}



	


	/////////////// DTO Converters ///////////////////////

	private Set<QuestionAnswerDto> detailsDtoConverter(Set<QuestionAnswerDefinition> questionAnswerDefinitionSet) {
		Set<QuestionAnswerDto> questionAnswerDTOSet = new HashSet<QuestionAnswerDto>();
		if (questionAnswerDefinitionSet != null) {
			for (QuestionAnswerDefinition questionAnswerDefinition : questionAnswerDefinitionSet) {
				QuestionAnswerDto model = new QuestionAnswerDto(questionAnswerDefinition);
				questionAnswerDTOSet.add(model);
			}
		}
		return questionAnswerDTOSet;

	}
	
	//////////////////////////////////////////////////////



}
