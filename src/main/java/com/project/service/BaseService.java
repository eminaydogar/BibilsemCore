package com.project.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.common.service.CoreContainerService;
import com.project.entity.UserDefinition;
import com.project.exception.AuthorizationException;
import com.project.security.role.RoleType;

import lombok.Getter;


@Service 
@Transactional
public abstract class BaseService {

	@Getter
	@Autowired
	private CoreContainerService coreContainerService;


	protected boolean authorized(UserDefinition user) throws AuthorizationException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || user == null) {
			throw new AuthorizationException("No authorization");
		}
		if (user.getUsername().equalsIgnoreCase(auth.getName())) {
			return true;
		} else {
			for (GrantedAuthority role : auth.getAuthorities()) {
				if (role.getAuthority().equalsIgnoreCase("ROLE_" + RoleType.ADMIN.getName())) {
					return true;
				}
			}
			throw new AuthorizationException("No authorization");
		}

	}
	

}


