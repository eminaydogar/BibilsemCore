package com.project.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.project.security.role.RoleType;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URLSecurity {
	
	String url() default "";
	RoleType[] accessType() default {};

}
