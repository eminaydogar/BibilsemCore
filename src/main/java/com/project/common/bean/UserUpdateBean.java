package com.project.common.bean;

import com.project.common.annotation.Mandatory;
import com.project.entity.UserDefinition;

import lombok.Getter;
import lombok.Setter;

public class UserUpdateBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3714880662896750981L;
	
	
	@Getter
	@Setter
	@Mandatory
	private Long userId;

	@Getter
	@Setter
	@Mandatory
	private String username;

	
	@Getter
	@Setter
	private String oldPassword;
	
	@Getter
	@Setter
	@Mandatory
	private String password;

	@Getter
	@Setter
	private Long phoneNumber;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String address;

	public UserUpdateBean() {
	}

	@Override
	public UserDefinition toEntity() {
		return null;
	}

}
