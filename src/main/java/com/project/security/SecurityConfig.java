package com.project.security;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import com.project.enums.UserRoleTYPE;
import com.project.security.annotation.URLSecurity;

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

		Map<String, List<URLSecurity>> securityMap = URLSecurityConfigurer.instance().getSecurityURLMap();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry v = http.csrf().disable()
				.authorizeRequests();

		if (securityMap.size() == 0) {
			v.antMatchers(path + "api/**").hasAnyRole(UserRoleTYPE.ADMIN.getName());
			return v;
		}
		for (String path : securityMap.keySet()) {
			List<URLSecurity> securityList = securityMap.get(path);
			for (URLSecurity security : securityList) {
				if (security.accessType() == null || security.accessType().length == 0) {
					v.antMatchers(path + security.url()).permitAll();
					log.info(path + security.url() + " permited all");
				} else {
					String[] roleString = new String[security.accessType().length];
					for (int i = 0; i < roleString.length; i++) {
						roleString[i] = security.accessType()[i].getName();
						log.info(path + security.url() + " has role for " + roleString[i]);
					}
					v.antMatchers(path + security.url()).hasAnyRole(roleString);
				}
			}
			v.antMatchers("/swagger-ui/*").hasAnyRole(UserRoleTYPE.ADMIN.getName());

		}
		return v;

	}

}
