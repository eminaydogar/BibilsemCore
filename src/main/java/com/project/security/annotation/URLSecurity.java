package com.project.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.project.enums.UserRoleTYPE;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URLSecurity {
	
	String url();
	UserRoleTYPE[] accessType() default {};

}
