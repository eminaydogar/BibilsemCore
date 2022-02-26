package com.project.common.dto;

import java.io.Serializable;
import java.sql.Clob;

import com.project.entity.PrizeDefinition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrizeDto implements IDto,Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4889448783044114627L;

	private Long id;
	
	private String prizeName;
	
	private Clob prizeImage;
	
	private Long price;
	
	private String status;
	

	public PrizeDto(PrizeDefinition model) {
		this.id = model.getId();
		this.prizeName = model.getPrizeName();
		this.prizeImage = model.getPrizeImage();
		this.price = model.getPrice();
		this.status=model.getStatus();

	}




}
