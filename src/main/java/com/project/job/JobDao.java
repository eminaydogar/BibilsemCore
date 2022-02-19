package com.project.job;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.annotation.Dao;

@Dao
public class JobDao {

	@PersistenceContext
	private EntityManager entityManager;

	private EntityManager getEntityManager() {
		return entityManager;
	}

	public JobBean initilazer(Long jobId) {
		Query query = getEntityManager().createNativeQuery("SELECT * FROM JOB_DEFINITION WHERE ID=?");
		query.setParameter(1, jobId);
		Object[] object = (Object[]) query.getSingleResult();
		JobBean bean = new JobBean(object);
		return bean;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeUpdate(String sql, Object... params) {

		Query query = getEntityManager().createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		query.executeUpdate();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> getResultList(String sql, Class clazz, Object... params) {
		Query query = getEntityManager().createNativeQuery(sql, clazz);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}

		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getResultList(String sql, Object... params) {
		Query query = getEntityManager().createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}

		return (List<Object[]>) query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getSingleResult(String sql, Class clazz, Object... params) {
		Query query = getEntityManager().createNativeQuery(sql, clazz);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}

		return (T) query.getSingleResult();
	}

	public Object getSingleResult(String sql, Object... params) {
		Query query = getEntityManager().createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.getSingleResult();
	}

	/*
	 * public Object executeSingleResultQuery(String sql, boolean
	 * inConditionInclude, Object... params) { Query q = null; if
	 * (inConditionInclude) { QueryBuilder qBuilder = new QueryBuilder();
	 * qBuilder.append(sql); qBuilder.filterIN(params); q =
	 * entityManager.createNativeQuery(qBuilder.toString()); } else { q =
	 * entityManager.createNativeQuery(sql); for (int i = 0; i < params.length; i++)
	 * { q.setParameter(i + 1, params[i]); } } return q.getSingleResult(); }
	 * 
	 * public Object executeSingleResultQuery(String sql, Object... params) { return
	 * executeSingleResultQuery(sql, false, params); }
	 * 
	 * public List<?> executeQuery(String sql, boolean inConditionInclude, Object...
	 * params) { Query q = null; if (inConditionInclude) { QueryBuilder qBuilder =
	 * new QueryBuilder(); qBuilder.append(sql); qBuilder.filterIN(params); q =
	 * entityManager.createNativeQuery(qBuilder.toString()); } else { q =
	 * entityManager.createNativeQuery(sql); for (int i = 0; i < params.length; i++)
	 * { q.setParameter(i + 1, params[i]); } } return q.getResultList(); }
	 * 
	 * public List<?> executeQuery(String sql, Object... params) { return
	 * executeQuery(sql, false, params); }
	 * 
	 * @SuppressWarnings("unchecked") public List<Object[]> executeQuery(String sql,
	 * List<Object> params) {
	 * 
	 * QueryBuilder qBuilder = new QueryBuilder(); qBuilder.append(sql);
	 * qBuilder.filterIN(params); Query q =
	 * entityManager.createNativeQuery(qBuilder.toString()); List<Object[]> rows =
	 * q.getResultList(); return rows; }
	 * 
	 * public void commit() { if (entityManager.isOpen()) {
	 * entityManager.getTransaction().commit(); }
	 * 
	 * }
	 * 
	 * public void rollback() { if (entityManager.isOpen()) {
	 * entityManager.getTransaction().rollback(); } }
	 */

}
