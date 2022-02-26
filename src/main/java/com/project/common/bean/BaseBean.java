package com.project.common.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.project.entity.RoleDefinition;
import com.project.exception.EntityNotFoundException;
import com.project.security.role.RoleType;

public abstract class BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Object toEntity() throws EntityNotFoundException;
	
	protected Set<RoleDefinition> setDefaultUserRole(){
		Set<RoleDefinition> roles = new HashSet<RoleDefinition>();
		RoleDefinition role = new RoleDefinition(RoleType.USER.getId(), RoleType.USER.getName());
		roles.add(role);
		return roles;
	}
	

}
