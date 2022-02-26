package com.project.common.business;

import java.util.List;

public interface IQueryManager {

	public void save(Object entity);

	public void saveOrUpdate(String sql, Object... params);

	public void saveOrUpdate(String sql);

	public <T> T get(Class<?> clazz, String sql, Object... params);

	public <T> List<T> getList(Class<?> clazz, String sql, Object... params);

	public Object getObject(String sql, Object... params);

	public Object getObjectList(String sql, Object... params);

}
