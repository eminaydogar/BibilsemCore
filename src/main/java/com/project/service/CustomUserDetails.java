package com.project.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.dto.RoleDto;
import com.project.dto.UserDto;




public class CustomUserDetails implements UserDetails{
	
	 private static final long serialVersionUID = 2396654715019746670L;
	 private final UserDto user;

	 public CustomUserDetails(UserDto user) {
		this.user=user;
	}
	 
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (RoleDto role : this.user.getRoles())
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		return authorities;
	}

    @JsonIgnore
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return user.getIsBlackList()==null||user.getIsBlackList().equalsIgnoreCase("N");
	}
	
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	


}
