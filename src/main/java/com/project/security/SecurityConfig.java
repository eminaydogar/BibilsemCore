package com.project.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import com.project.enums.UserRoleTYPE;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${server.servlet.context-path}")
	private String path;

	private final CustomAuthenticationProvider authProvider;

	public SecurityConfig(CustomAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		configureCustomAuthorize(http).anyRequest().authenticated().and().httpBasic();
	}

	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureCustomAuthorize(
			HttpSecurity http) throws Exception {

		Map<String, UserRoleTYPE[]> securityMap = URLSecurityConfigurer.instance().getURLSecurityMap();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry v = http.csrf().disable()
				.authorizeRequests();

		if (securityMap.size() == 0) {
			v.antMatchers(path + "api/**").hasAnyRole(UserRoleTYPE.ADMIN.getName());
			return v;
		}
		for (String path : securityMap.keySet()) {
			UserRoleTYPE[] roles = securityMap.get(path);
			if(roles!=null && roles.length>0) {
				String[] roleString = new String[roles.length];
				for (int i = 0; i < roleString.length; i++) {
					roleString[i] = roles[i].getName();
					log.info(path  + " has role for " + roleString[i]);
				}
				v.antMatchers(path).hasAnyRole(roleString);
			}else {
				v.antMatchers(path).permitAll();
				log.info(path + " permited all");
			}
		}
		
		v.antMatchers("/swagger-ui/*").hasAnyRole(UserRoleTYPE.ADMIN.getName());
		
		return v;

	}

}
