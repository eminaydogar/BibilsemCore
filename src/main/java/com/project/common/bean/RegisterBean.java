package com.project.common.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.project.common.annotation.Mandatory;
import com.project.entity.UserDefinition;
import com.project.security.PasswordCrypter;
import com.project.utility.SecureUtility;

import lombok.Getter;
import lombok.Setter;

public class RegisterBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Mandatory
	@NotNull(message = "user name couldnt be null")
	private String username;

	@Getter
	@Setter
	@Mandatory
	private String password;

	@Getter
	@Setter
	private Long phoneNumber;

	@Getter
	@Setter
	@Mandatory
	private String email;

	@Getter
	@Setter
	@Mandatory
	private String address;

	public RegisterBean() {
	}

	@Override
	public UserDefinition toEntity() {
		UserDefinition model = new UserDefinition();
		model.setUsername(username);
		model.setPassword(PasswordCrypter.instance().encode(password));
		model.setBbPoint(1000L);
		model.setEmail(email);
		model.setPhoneNumber(SecureUtility.getInstance().phoneNumberCrypter(phoneNumber));
		model.setRoles(setDefaultUserRole());
		model.setAddress(SecureUtility.getInstance().encrypt(address));
		model.setCdate(new Date());
		model.setVertify("N");
		return model;
	}
	
	
	

}
