package com.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.LoginBean;
import com.project.bean.RegisterBean;
import com.project.bean.UserVerificationBean;
import com.project.controller.url.LoginURL;
import com.project.entity.BBResponse;

@RequestMapping(LoginURL.basePath)
public interface ILoginController {

	@PostMapping(value = LoginURL.register)
	BBResponse<?> register(@RequestBody RegisterBean bean);
	
	@PostMapping(value = LoginURL.login)
	BBResponse<?> login(@RequestBody LoginBean bean);
	
	@PostMapping(value = LoginURL.confirmUser)
	BBResponse<?> confirmUser(@RequestBody UserVerificationBean bean);
	

	
	
}
