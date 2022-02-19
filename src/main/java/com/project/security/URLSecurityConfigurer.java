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

import com.project.enums.UserRoleTYPE;
import com.project.security.annotation.URLSecurity;
import com.project.security.annotation.URLSecurityReflection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class URLSecurityConfigurer {

	private static URLSecurityConfigurer SINGLETON = null;

	private static char slash = '/';

	private static Map<String, UserRoleTYPE[]> urlSecurityMap = new HashMap<String, UserRoleTYPE[]>();

	private URLSecurityConfigurer() {
		try {
			urlSecurityMap = getURLSecurityMap();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	public static URLSecurityConfigurer instance() {
		if (SINGLETON == null) {
			SINGLETON = new URLSecurityConfigurer();
		}
		return SINGLETON;
	}

	public Map<String, UserRoleTYPE[]> getURLSecurityMap()
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		if (urlSecurityMap != null && urlSecurityMap.size() > 0) {
			return urlSecurityMap;
		}
		fillSecurityMap(getClasses());
		return urlSecurityMap;
	}

	private void fillSecurityMap(List<Class<?>> classList) throws IllegalArgumentException, IllegalAccessException {
		for (Class<?> c : classList) {
			URLSecurityReflection mainVal = c.getAnnotation(URLSecurityReflection.class);
			if (!mainVal.baseURL().equals("")) {
				
				String basePath = mainVal.baseURL();
				
				if (basePath.charAt(0) != slash) {
					
					basePath = slash + basePath;
				}

				Field[] fields = c.getDeclaredFields();
				
				for (Field field : fields) {
					
					URLSecurity val = field.getAnnotation(URLSecurity.class);
					
					if (val != null) {
						
						if (val.url() != null && !val.url().equals("")) {
							
							urlSecurityMap.put(basePath + val.url(), val.accessType());

						} else {
							
							String url = (String) field.get(field);
							
							urlSecurityMap.put(basePath + url, val.accessType());

						}
					}
				}
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
