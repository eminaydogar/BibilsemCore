package com.project.common.service;

import com.project.entity.LogDefinition;

public interface ILoggerService {

	public void delete(Long id);
	
	public LogDefinition getById(Long id);
	
	public void writeErrorLog(String className, String methodName, String errorType, Exception exception);
	
	public void writeLog(String className, String methodName,
			String message);

}
