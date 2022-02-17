package com.project.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.project.entity.UserDefinition;
import com.project.enums.UserRoleTYPE;
import com.project.exception.AuthorizationException;
import com.project.mail.EmailService;
import com.project.mail.EmailServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public abstract class BaseService {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private EmailServiceImpl mailSender;

	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(Object entity) {
		try {
			manager.persist(entity);
		} catch (Exception e) {
			log.error("(save)" + e);
			manager.getTransaction().rollback();
		}

	}

	@SuppressWarnings("unchecked")
	public <T> T select(Class<?> clazz, String sql, Object... params) {
		T result = null;
		try {
			Query query = manager.createNativeQuery(sql, clazz);
			for (int i = 1; i <= params.length; i++) {
				query.setParameter(i, params[i - 1]);
			}
			result = (T) query.getSingleResult();
		} catch (Exception e) {
			log.error("(select)" + e);
		}
		return result;

	}



	protected boolean authorized(UserDefinition user) throws AuthorizationException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || user == null) {
			throw new AuthorizationException("No authorization");
		}
		if (user.getUsername().equalsIgnoreCase(auth.getName())) {
			return true;
		} else {
			for (GrantedAuthority role : auth.getAuthorities()) {
				if (role.getAuthority().equalsIgnoreCase("ROLE_" + UserRoleTYPE.ADMIN.getName())) {
					return true;
				}
			}
			throw new AuthorizationException("No authorization");
		}

	}
	
	protected EmailService getMailSender() {
		return mailSender;
	}

}


