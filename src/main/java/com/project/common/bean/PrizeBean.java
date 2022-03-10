package com.project.common.bean;

import java.util.Date;

import com.project.cache.BBConstant.RECORD_STATUS;
import com.project.common.annotation.Mandatory;
import com.project.entity.PrizeDefinition;
import com.project.exception.EntityNotFoundException;

import lombok.Getter;
import lombok.Setter;

public class PrizeBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6493688096601908849L;

	@Getter
	@Setter
	private Long prizeId;

	@Getter
	@Setter
	@Mandatory
	private String prizeName;

	@Getter
	@Setter
	private String prizeImage;

	@Getter
	@Setter
	@Mandatory
	private Long price;
	
	@Getter
	@Setter
	private Date cdate;
	
	@Getter
	@Setter
	private String status;
	
	@Override
	public PrizeDefinition toEntity() throws EntityNotFoundException {
		PrizeDefinition model = new PrizeDefinition();
		model.setPrizeName(prizeName);
		model.setPrice(price);
		model.setStatus(RECORD_STATUS.OK);
		model.setCdate(new Date());
		return model;
	}

}
