package com.project.common.service;

import org.springframework.stereotype.Service;

import com.project.common.business.CoreManager;
import com.project.utility.QueryBuilder;

import lombok.Setter;

@Service
public class LoggerService {

	private final static String LOG_INSERT_SQL = "INSERT INTO LOG_DEFINITION(CLASS_NAME,METHOD_NAME,ERROR_TYPE,ERROR_MESSAGE,MESSAGE,CDATE) VALUES(?,?,?,?,?,NOW())";

	@Setter
	private CoreManager coreManager = null;

	public boolean save(Class<?> clazz, String methodName, String errorType, Exception exception, String message) {
		try {

			QueryBuilder queryBuilder = new QueryBuilder();

			queryBuilder.append(LOG_INSERT_SQL);

			queryBuilder.setParam(clazz.getSimpleName());

			queryBuilder.setParam(methodName);

			queryBuilder.setParam(errorType);

			queryBuilder.setParam(exception.getMessage());

			queryBuilder.setParam(message);

			String sqlString = queryBuilder.toString();

			coreManager.saveOrUpdate(sqlString);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
