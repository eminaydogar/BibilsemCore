package com.project.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.project.entity.PrizeDefinition;
import com.project.entity.PrizeRequestDefinition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrizeRequestDto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668867051958775054L;

	Long id;

	PrizeDto prize;

	private Date cdate;

	private Date edate;

	private String requestStatus;

	private String statusDescription;

	public PrizeRequestDto(PrizeRequestDefinition model) {
		this.id = model.getId();
		this.prize = prizeDtoConverter(model.getPrize());
		this.cdate = model.getCdate();
		this.edate = model.getEdate();
		this.requestStatus = model.getRequestStatus();
		this.statusDescription = model.getStatusDescription();
	}

	private PrizeDto prizeDtoConverter(PrizeDefinition model) {
		PrizeDto dtoModel = new PrizeDto(model.getId(), model.getPrizeName(), model.getPrizeImage(), model.getPrice(),
				model.getStatus());
		return dtoModel;
	}

}
