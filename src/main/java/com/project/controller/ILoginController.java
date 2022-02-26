package com.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.common.bean.LoginBean;
import com.project.common.bean.RegisterBean;
import com.project.common.bean.UserVerificationBean;
import com.project.controller.url.LoginURL;
import com.project.entity.BBResponse;

import io.swagger.annotations.Api;

@Api(tags = "Login Service")
@RequestMapping(LoginURL.basePath)
public interface ILoginController {

	@PostMapping(value = LoginURL.register)
	BBResponse<?> register(@RequestBody RegisterBean bean);
	
	@PostMapping(value = LoginURL.login)
	BBResponse<?> login(@RequestBody LoginBean bean);
	
	@PostMapping(value = LoginURL.confirmUser)
	BBResponse<?> confirmUser(@RequestBody UserVerificationBean bean);
	

	
	
}
