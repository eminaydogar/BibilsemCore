package com.project.common.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.transaction.annotation.Propagation;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
public class CoreManager implements ICoreManager {

	@Setter
	private EntityManager manager;

	public CoreManager() {
	}

	public CoreManager(EntityManager managaer) {
		this.manager = managaer;
	}

	public boolean isSessionActive() {
		if (manager != null || manager.getTransaction().isActive()) {
			return true;
		}
		return false;
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(Object entity) {
		try {
			manager.persist(entity);
		} catch (Exception e) {
			log.error("(save)" + e);
			manager.getTransaction().rollback();
		}

	}

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)
	public void update(Object entity) {
		try {
			manager.merge(entity);
		} catch (Exception e) {
			log.error("(merge)" + e);
			manager.getTransaction().rollback();
		}

	}

	@Override
	public <T> T findById(Class<T> clazz, Long id) {
		T result = null;
		try {
			result = manager.find(clazz, id);
		} catch (Exception e) {
			log.error("(findById)" + e);
		}
		return result;

	}

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveOrUpdate(String sql, Object... params) {
		try {
			Query query = manager.createNativeQuery(sql);
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}
			query.executeUpdate();
		} catch (Exception e) {
			log.error("(saveOrUpdate)" + e);
			manager.getTransaction().rollback();
		}

	}

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveOrUpdate(String sql) {
		saveOrUpdate(sql, null);
	}

	@org.springframework.transaction.annotation.Transactional
	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> clazz, String sql, Object... params) {
		T result = null;
		try {
			Query query = manager.createNativeQuery(sql, clazz);
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}
			result = (T) query.getSingleResult();
		} catch (Exception e) {
			log.error("(get)" + e);
		}
		return result;

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(Class<?> clazz, String sql, Object... params) {
		List<T> result = null;
		try {
			Query query = manager.createNativeQuery(sql, clazz);
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}
			result = (List<T>) query.getResultList();
		} catch (Exception e) {
			log.error("(getList)" + e);
		}
		return result;

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Object[] getObject(String sql, Object... params) {
		Object[] result = null;
		try {
			Query query = manager.createNativeQuery(sql);
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}
			result = (Object[]) query.getSingleResult();
		} catch (Exception e) {
			log.error("(getObject)" + e);
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	@org.springframework.transaction.annotation.Transactional
	public List<Object[]> getObjectList(String sql, Object... params) {
		List<Object[]> result = null;
		try {
			Query query = manager.createNativeQuery(sql);
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					query.setParameter(i, params[i - 1]);
				}
			}
			result = query.getResultList();
		} catch (Exception e) {
			log.error("(getObjectList)" + e);
		}
		return result;
	}

}
