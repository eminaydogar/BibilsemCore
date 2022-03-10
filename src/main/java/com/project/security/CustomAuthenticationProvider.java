package com.project.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.security.service.UserSecurityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserSecurityService securityService;

	public CustomAuthenticationProvider(UserSecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = securityService.loadUserByUsername(username);
		
		if (!username.equalsIgnoreCase(user.getUsername())
				|| !PasswordCrypter.instance().matches(password, user.getPassword())) {
			log.info("user not found");
			throw new AuthenticationServiceException("User not found ! Check username or password");
		}else if(!user.isAccountNonLocked()) {
			log.info("user locked");
			throw new AuthenticationServiceException("User lock !");
		}

		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}