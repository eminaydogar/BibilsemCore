package com.project.job;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.project.common.annotation.Dao;
import com.project.common.business.CoreManager;

import lombok.Getter;

@Dao
@Transactional
public class JobDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Getter
	private final CoreManager coreManager = new CoreManager();
	
	@PostConstruct
	void init() {
		coreManager.setManager(entityManager);
	}

	public JobBean initilazer(Long jobId) {
		Object[] object = null;//coreManager.getObject("SELECT * FROM JOB_DEFINITION WHERE ID=?", jobId);
		JobBean bean = new JobBean(object);
		return bean;
	}

}
