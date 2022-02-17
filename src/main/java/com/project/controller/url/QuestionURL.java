package com.project.controller.url;

import com.project.enums.UserRoleTYPE;
import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;

@URLSecurityReflection(baseURL = QuestionURL.basePath)
public class QuestionURL {
	public static final String basePath="api/questions";

	@URLSecurity(url = "/save",accessType = {UserRoleTYPE.ADMIN})
	public static final String save = "/save";

	@URLSecurity(url = "/update",accessType = {UserRoleTYPE.ADMIN})
	public static final String update = "/update";

	@URLSecurity(url = "/refresh",accessType = {UserRoleTYPE.ADMIN})
	public static final String refresh = "/refresh";

	@URLSecurity(url = "/inactive/{id}",accessType = {UserRoleTYPE.ADMIN})
	public static final String inactive = "/inactive";
}
