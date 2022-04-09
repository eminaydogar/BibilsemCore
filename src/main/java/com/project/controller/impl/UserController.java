package com.project.controller.impl;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.bean.UserCouponBean;
import com.project.common.bean.UserPrizeRequestBean;
import com.project.common.bean.UserUpdateBean;
import com.project.controller.IUserController;
import com.project.entity.BBResponse;
import com.project.service.IUserService;
import com.project.service.impl.UserService;

@RestController
public class UserController implements IUserController {

	private final IUserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@Override
	public BBResponse<?> saveCoupon(UserCouponBean bean) {
		return service.saveCoupon(bean);
	}

	@Override
	public BBResponse<?> savePrize(UserPrizeRequestBean bean) {
		return service.savePrizeRequest(bean);
	}

	@Override
	public BBResponse<?> updateUser(@RequestBody UserUpdateBean bean) {
		return service.updateUser(bean);
	}

	@Override
	public BBResponse<?> getWealthyUsers() {
		return service.findWealthyUsers();
	}

}


