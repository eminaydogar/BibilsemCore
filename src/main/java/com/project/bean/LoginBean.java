package com.project.bean;

import com.project.annotation.Mandatory;

import lombok.Getter;
import lombok.Setter;


public class LoginBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Mandatory
	private String username;
	
	@Getter
	@Setter
	@Mandatory
	private String password;

	@Override
	public Object toEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
