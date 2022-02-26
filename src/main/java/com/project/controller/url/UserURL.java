package com.project.controller.url;
import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;
import com.project.security.role.RoleType;

@URLSecurityReflection(baseURL = UserURL.basePath)
public class UserURL {
	
	public static final String basePath = "api/users";
	
	@URLSecurity(accessType = {RoleType.USER,RoleType.ADMIN})
	public static final String saveCoupon = "/saveCoupon";
	
	@URLSecurity(accessType = {RoleType.USER,RoleType.ADMIN})
	public static final String updateUser = "/updateUser";

	@URLSecurity(accessType = {RoleType.USER,RoleType.ADMIN})
	public static final String updateUserPrizeRequest = "/savePrizeRequest";
	
	@URLSecurity(accessType = {RoleType.USER,RoleType.ADMIN})
	public static final String savePrize = "/savePrize";
	
	
	////////////// only admins///////////////////////////////////////////////77

	@URLSecurity(url = "/inactive", accessType = {RoleType.ADMIN})
	public static final String inactive = "/inactive";
	
	@URLSecurity(url = "/updateRole", accessType = {RoleType.ADMIN})
	public static final String updateRole = "/updateRole";
}
