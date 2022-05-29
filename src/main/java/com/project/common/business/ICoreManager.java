package com.project.common.business;

import java.util.List;

public interface ICoreManager {

	public void save(Object entity);

	public void saveOrUpdate(String sql, Object... params);

	public void saveOrUpdate(String sql);

	public <T> T get(Class<?> clazz, String sql, Object... params);

	public <T> List<T> getList(Class<?> clazz, String sql, Object... params);

	void update(Object entity);

	public <T> T findById(Class<T> clazz, Long id);

	public <T> List<T> getList(Class<?> clazz, String sql, List<Long> ids);

}
