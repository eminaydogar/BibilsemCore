package com.project.common.service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.business.CoreManager;
import com.project.mail.EmailService;

import lombok.Getter;

@Service
@Transactional
public class CoreContainerService {

	@PersistenceContext
	private EntityManager manager;

	@Getter
	private final CoreManager coreManager = new CoreManager();

	@Getter
	@Autowired
	private EmailService mailService;

	@Getter
	@Autowired
	private ILoggerService loggerService;

	@PostConstruct
	void init() {
		coreManager.setManager(manager);
	}



}
