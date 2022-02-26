package com.project.controller.url;

import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;
import com.project.security.role.RoleType;

@URLSecurityReflection(baseURL = JobURL.baseURL)
public class JobURL {
	
	public static final String baseURL = "api/job";
	
	@URLSecurity(accessType = {RoleType.ADMIN,RoleType.DIRECTOR})
	public static final String stopJob = "/stop";
	@URLSecurity(accessType = {RoleType.ADMIN,RoleType.DIRECTOR})
	public static final String startJob = "/start";
}
