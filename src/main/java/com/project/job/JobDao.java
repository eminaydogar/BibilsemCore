package com.project.job;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.project.common.annotation.Dao;
import com.project.common.business.QueryManager;

import lombok.Getter;

@Dao
@Transactional
public class JobDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Getter
	private final QueryManager queryManager = new QueryManager();
	
	@PostConstruct
	void init() {
		queryManager.setManager(entityManager);
	}

	public JobBean initilazer(Long jobId) {
		Object[] object = queryManager.getObject("SELECT * FROM JOB_DEFINITION WHERE ID=?", jobId);
		JobBean bean = new JobBean(object);
		return bean;
	}

}
