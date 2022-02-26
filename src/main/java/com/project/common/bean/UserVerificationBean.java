package com.project.common.bean;

import com.project.common.annotation.Mandatory;
import com.project.exception.EntityNotFoundException;

import lombok.Getter;
import lombok.Setter;

public class UserVerificationBean extends BaseBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3650442967242415210L;
	
	@Getter
	@Setter
	@Mandatory
	private Long userId;
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	@Mandatory
	private String email;
	@Getter
	@Setter
	@Mandatory
	private String verificationCode;

	@Override
	public Object toEntity() throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
