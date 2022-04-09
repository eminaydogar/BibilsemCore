package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.common.bean.UserCouponBean;
import com.project.common.bean.UserPrizeRequestBean;
import com.project.common.bean.UserUpdateBean;
import com.project.controller.url.UserURL;
import com.project.entity.BBResponse;

import io.swagger.annotations.Api;


@Api(tags = "User Service",value = "Info")
@RequestMapping(UserURL.basePath)
public interface IUserController {

	@PostMapping(value = UserURL.saveCoupon)
	public BBResponse<?> saveCoupon(@RequestBody UserCouponBean bean);

	@PostMapping(value = UserURL.savePrize)
	public BBResponse<?> savePrize(@RequestBody UserPrizeRequestBean bean);

	@PostMapping(value=UserURL.updateUser)
	public BBResponse<?> updateUser(@RequestBody UserUpdateBean bean);
	
	@GetMapping(value=UserURL.getWealthyUsers)
	public BBResponse<?> getWealthyUsers();

}
