package com.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.UserCouponBean;
import com.project.bean.UserPrizeRequestBean;
import com.project.bean.UserUpdateBean;
import com.project.controller.url.UserURL;
import com.project.entity.BBResponse;

@RequestMapping(UserURL.basePath)
public interface IUserController {

	@PostMapping(value = UserURL.saveCoupon)
	public BBResponse<?> saveCoupon(@RequestBody UserCouponBean bean);

	@PostMapping(value = UserURL.savePrize)
	public BBResponse<?> savePrize(@RequestBody UserPrizeRequestBean bean);

	@PostMapping(value=UserURL.updateUser)
	public BBResponse<?> updateUser(@RequestBody UserUpdateBean bean);

}
