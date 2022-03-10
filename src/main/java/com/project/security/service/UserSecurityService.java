package com.project.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.common.dto.UserDto;
import com.project.entity.UserDefinition;
import com.project.service.BaseService;
import com.project.service.SQLCache;

@Service
public class UserSecurityService extends BaseService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		return new CustomUserDetails(user);
	}

	public UserDto findByUsername(String username) {
	//	UserDefinition user = select(UserDefinition.class, SQLCache.SELECT.BY_USERNAME, username);
		UserDefinition user = getCoreContainerService().getCoreManager().get(UserDefinition.class, SQLCache.SELECT.BY_USERNAME, username);
		if(user==null) return null;
		return new UserDto().withRoles(user);
	}

}
