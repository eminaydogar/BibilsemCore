package com.project.controller.impl;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.UserCouponBean;
import com.project.bean.UserPrizeRequestBean;
import com.project.bean.UserUpdateBean;
import com.project.controller.IUserController;
import com.project.entity.BBResponse;
import com.project.service.IUserService;
import com.project.service.impl.UserService;

import io.swagger.annotations.Api;
@Api(tags = "User Service",value = "Info")
@RestController
public class UserController implements IUserController{

	private final IUserService service;

	public UserController(UserService service) {
		this.service=service;
	}
	
	@Override
	public BBResponse<?> saveCoupon(UserCouponBean bean) {
		return service.saveCoupon(bean);
	}

	@Override
	public BBResponse<?> savePrize(UserPrizeRequestBean bean) {
		// TODO Auto-generated method stub
		return service.savePrizeRequest(bean);
	}

	@Override
	public BBResponse<?> updateUser(@RequestBody UserUpdateBean bean ) {
		// TODO Auto-generated method stub
		return null;
	}


}



















/*
 * @GetMapping(value = UserURL.selectFullUser) public BBResponse<?>
 * findById(@PathVariable Long id) { return
 * service.execute(OperationCODE.USER_SELECT, injectPathVariableToDto(id)); }
 * 
 * @PutMapping(value = UserURL.updateUser) public BBResponse<?>
 * updateUser(@RequestBody UserDto user) { return
 * service.execute(OperationCODE.USER_UPDATE, user); }
 * 
 * @PutMapping(value = UserURL.saveCoupon) public BBResponse<?>
 * updateUserCoupon(@RequestBody UserDto user) { return
 * service.execute(OperationCODE.USER_COUPON_UPDATE, user); }
 * 
 * @PostMapping(value=UserURL.savetUser) public BBResponse<?>
 * insertUser(@RequestBody UserDto user) { return
 * service.execute(OperationCODE.USER_INSERT, user); }
 * 
 * @PutMapping(value = UserURL.updateUserRole) public BBResponse<?>
 * updateUserRole(@RequestBody UserDto user) { return
 * service.execute(OperationCODE.USER_ROLE_UPDATE, user); }
 * 
 * @PutMapping(value = UserURL.updateUserPrizeRequest) public BBResponse<?>
 * updateUserPrizeRequest(@RequestBody UserDto user) { return
 * service.execute(OperationCODE.USER_PRIZE_UPDATE, user); }
 */


/*
 * private UserDto injectPathVariableToDto(Object value) { UserDto dto = new
 * UserDto(); if (value instanceof Long) { dto.setId((long) value); } else if
 * (value instanceof String) { dto.setUsername(value.toString()); } return dto;
 * }
 */
