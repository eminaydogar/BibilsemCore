package com.project.controller.url;
import com.project.enums.UserRoleTYPE;
import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;

@URLSecurityReflection(baseURL = UserURL.basePath)
public class UserURL {
	
	public static final String basePath = "api/users";
	
	@URLSecurity(url = "/saveCoupon", accessType = {UserRoleTYPE.USER,UserRoleTYPE.ADMIN})
	public static final String saveCoupon = "/saveCoupon";
	
	@URLSecurity(url = "/updateUser", accessType = {UserRoleTYPE.USER,UserRoleTYPE.ADMIN})
	public static final String updateUser = "/updateUser";

	@URLSecurity(url = "/savePrizeRequest", accessType = {UserRoleTYPE.USER,UserRoleTYPE.ADMIN})
	public static final String updateUserPrizeRequest = "/savePrizeRequest";
	
	@URLSecurity(url = "/savePrize", accessType = {UserRoleTYPE.USER,UserRoleTYPE.ADMIN})
	public static final String savePrize = "/savePrize";
	
	
	////////////// only admins///////////////////////////////////////////////77

	@URLSecurity(url = "/inactive", accessType = {UserRoleTYPE.ADMIN})
	public static final String inactive = "/inactive";
	
	@URLSecurity(url = "/updateRole", accessType = {UserRoleTYPE.ADMIN})
	public static final String updateRole = "/updateRole";
}
