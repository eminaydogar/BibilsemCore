package com.project.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.project.common.bean.LoginBean;
import com.project.common.bean.RegisterBean;
import com.project.common.bean.UserVerificationBean;
import com.project.controller.ILoginController;
import com.project.entity.BBResponse;
import com.project.service.IUserService;
import com.project.service.impl.UserService;

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
