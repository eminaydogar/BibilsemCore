package com.project.common.dto;

import java.io.Serializable;

import com.project.entity.RoleDefinition;

import lombok.Data;

@Data
public class RoleDto implements IDto,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6704737554003440426L;
	private Long id;
	private String name;
	
	public RoleDto(RoleDefinition role) {
		this.id=role.getId();
		this.name=role.getName();
	}
	
	public RoleDto() {
	}
	

	
	

	
	





}
