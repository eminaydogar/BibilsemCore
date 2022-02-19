package com.project.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.project.bean.LoginBean;
import com.project.bean.RegisterBean;
import com.project.bean.UserVerificationBean;
import com.project.controller.ILoginController;
import com.project.entity.BBResponse;
import com.project.service.IUserService;
import com.project.service.impl.UserService;

import io.swagger.annotations.Api;
@Api(tags = "Login Service")
@RestController
public class LoginController implements ILoginController {

	private final IUserService service;

	public LoginController(UserService service) {
		this.service = service;
	}

	@Override
	public BBResponse<?> register(RegisterBean bean) {
		return service.register(bean);
	}

	@Override
	public BBResponse<?> login(LoginBean bean) {
		return service.login(bean);
	}

	@Override
	public BBResponse<?> confirmUser(UserVerificationBean bean) {
		return service.confirmUser(bean);
	}

}
