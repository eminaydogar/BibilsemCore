package com.project.common.bean;

import java.util.Date;

import com.project.common.annotation.Mandatory;
import com.project.entity.PrizeRequestDefinition;
import com.project.exception.EntityNotFoundException;

import lombok.Getter;
import lombok.Setter;

public class UserPrizeRequestBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4814237060254886086L;
	private final String waiting = "Waiting";

	@Getter
	@Setter
	@Mandatory
	private Long userId;
	@Getter
	@Setter
	@Mandatory
	private Long prizeId;

	@Override
	public PrizeRequestDefinition toEntity() throws EntityNotFoundException {
		PrizeRequestDefinition model = new PrizeRequestDefinition();
		model.setCdate(new Date());
		/*PrizeDefinition prize = PrizeCache.getContext().getById(prizeId);
		if (prize == null) {
			throw new EntityNotFoundException("Nonexistent prize value");
		}
		model.setPrize(prize); */
		model.setRequestStatus("W");
		model.setStatusDescription(waiting);
		return model;
	}

}
