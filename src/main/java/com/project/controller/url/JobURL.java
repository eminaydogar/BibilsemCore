package com.project.controller.url;

import com.project.enums.UserRoleTYPE;
import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;

@URLSecurityReflection(baseURL = JobURL.baseURL)
public class JobURL {
	
	public static final String baseURL = "api/job";
	
	@URLSecurity(accessType = {UserRoleTYPE.ADMIN,UserRoleTYPE.DIRECTOR})
	public static final String stopJob = "/stop";
	@URLSecurity(accessType = {UserRoleTYPE.ADMIN,UserRoleTYPE.DIRECTOR})
	public static final String startJob = "/start";
}
