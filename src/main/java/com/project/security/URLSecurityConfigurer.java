package com.project.security;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;

@Component
public class URLSecurityConfigurer {

	private static URLSecurityConfigurer SINGLETON = null;

	private static char slash = '/';

	private static Map<String, List<URLSecurity>> urlSecurityMap = new HashMap<String, List<URLSecurity>>();

	private URLSecurityConfigurer() {
		try {
			urlSecurityMap = getSecurityURLMap();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static URLSecurityConfigurer instance() {
		if (SINGLETON == null) {
			SINGLETON = new URLSecurityConfigurer();
		}
		return SINGLETON;
	}

	public Map<String, List<URLSecurity>> getSecurityURLMap() throws ClassNotFoundException {
		if (urlSecurityMap != null && urlSecurityMap.size() > 0) {
			return urlSecurityMap;
		}
		pushSecurityList(getClasses());
		return urlSecurityMap;
	}

	private void pushSecurityList(List<Class<?>> classList) {
		for (Class<?> c : classList) {
			URLSecurityReflection mainVal = c.getAnnotation(URLSecurityReflection.class);
			if (!mainVal.baseURL().equals("")) {
				String basePath = mainVal.baseURL();
				if (basePath.charAt(0) != slash) {
					basePath = slash + basePath;
				}
				List<URLSecurity> securityList = new ArrayList<URLSecurity>();
				Field[] fields = c.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					URLSecurity val = fields[i].getAnnotation(URLSecurity.class);
					if (val != null) {
						securityList.add(val);
					}
				}
				urlSecurityMap.put(basePath, securityList);
			}

		}
	}

	private List<Class<?>> getClasses() throws ClassNotFoundException {
		List<Class<?>> clazzs = new ArrayList<Class<?>>();
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(URLSecurityReflection.class));
		for (BeanDefinition bd : scanner.findCandidateComponents("com.project")) {
			clazzs.add(Class.forName(bd.getBeanClassName()));
		}
		return clazzs;
	}

}
