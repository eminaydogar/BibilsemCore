package com.project.service;

import java.util.List;

import com.project.common.bean.LoginBean;
import com.project.common.bean.RegisterBean;
import com.project.common.bean.UserCouponBean;
import com.project.common.bean.UserPrizeRequestBean;
import com.project.common.bean.UserUpdateBean;
import com.project.common.bean.UserVerificationBean;
import com.project.common.dto.UserDto;
import com.project.entity.BBResponse;

public interface IUserService {
	
	public BBResponse<UserDto> register(RegisterBean bean);

	public BBResponse<UserDto> confirmUser(UserVerificationBean bean);

	public BBResponse<UserDto> login(LoginBean bean);

	public BBResponse<UserDto> saveCoupon(UserCouponBean bean);

	public BBResponse<UserDto> savePrizeRequest(UserPrizeRequestBean bean);

	public BBResponse<UserDto> updateUser(UserUpdateBean bean);

	public BBResponse<List<UserDto>> findWealthyUsers();
}
