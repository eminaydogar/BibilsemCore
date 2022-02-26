package com.project.controller.url;

import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;
import com.project.security.role.RoleType;

@URLSecurityReflection(baseURL = QuestionURL.basePath)
public class QuestionURL {
	public static final String basePath="api/questions";

	@URLSecurity(accessType = {RoleType.ADMIN})
	public static final String save = "/save";

	@URLSecurity(accessType = {RoleType.ADMIN})
	public static final String update = "/update";

	@URLSecurity(accessType = {RoleType.ADMIN})
	public static final String refresh = "/refresh";

	@URLSecurity(accessType = {RoleType.ADMIN})
	public static final String inactive = "/inactive";

	@URLSecurity(accessType = {RoleType.ADMIN,RoleType.USER})
	public static final String getAll = "/getAll";
}
