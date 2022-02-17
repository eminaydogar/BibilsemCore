package com.project.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.project.entity.QuestionAnswerDefinition;
import com.project.entity.RoleDefinition;
import com.project.enums.UserRoleTYPE;
import com.project.exception.EntityNotFoundException;

public abstract class BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Object toEntity() throws EntityNotFoundException;
	
	protected Set<RoleDefinition> setDefaultUserRole(){
		Set<RoleDefinition> roles = new HashSet<RoleDefinition>();
		RoleDefinition role = new RoleDefinition(UserRoleTYPE.USER.getId(), UserRoleTYPE.USER.getName());
		roles.add(role);
		return roles;
	}
	

}
