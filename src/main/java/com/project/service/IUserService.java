package com.project.service;

import com.project.bean.LoginBean;
import com.project.bean.RegisterBean;
import com.project.bean.UserCouponBean;
import com.project.bean.UserPrizeRequestBean;
import com.project.bean.UserUpdateBean;
import com.project.bean.UserVerificationBean;
import com.project.dto.UserDto;
import com.project.entity.BBResponse;

public interface IUserService {
	public BBResponse<UserDto> register(RegisterBean bean);
	public BBResponse<UserDto> confirmUser(UserVerificationBean bean);
	public BBResponse<UserDto> login(LoginBean bean);
	public BBResponse<UserDto> saveCoupon(UserCouponBean bean);
	public BBResponse<UserDto> savePrizeRequest(UserPrizeRequestBean bean);
	public BBResponse<UserDto> updateUser(UserUpdateBean bean);
}
