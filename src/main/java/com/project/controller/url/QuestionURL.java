package com.project.controller.url;

import com.project.enums.UserRoleTYPE;
import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;

@URLSecurityReflection(baseURL = QuestionURL.basePath)
public class QuestionURL {
	public static final String basePath="api/questions";

	@URLSecurity(accessType = {UserRoleTYPE.ADMIN})
	public static final String save = "/save";

	@URLSecurity(accessType = {UserRoleTYPE.ADMIN})
	public static final String update = "/update";

	@URLSecurity(accessType = {UserRoleTYPE.ADMIN})
	public static final String refresh = "/refresh";

	@URLSecurity(accessType = {UserRoleTYPE.ADMIN})
	public static final String inactive = "/inactive";
}
