package com.project.controller.url;

import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;

@URLSecurityReflection(baseURL = LoginURL.basePath)
public class LoginURL {
	
	public static final String basePath="api/login";

	@URLSecurity(url = "/register")
	public static final String register = "/register";
	
	@URLSecurity(url = "/login")
	public static final String login = "/login";
	
	@URLSecurity(url = "/confirmUser")
	public static final String confirmUser = "/confirmUser";
	
	
}
