package com.project.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.entity.LogDefinition;
import com.project.repository.LogRepository;

import lombok.Setter;

@Service
public class LoggerService implements ILoggerService {

	@Autowired
	@Setter
	private LogRepository repo;



	@Override
	public void delete(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public LogDefinition getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Async
	@Override
	public void writeErrorLog(String className, String methodName, String errorType, Exception exception) {
		LogDefinition logDefinition = new LogDefinition();
		logDefinition.setClassName(className);
		logDefinition.setMethodName(methodName);
		logDefinition.setErrorType(errorType);
		logDefinition.setErrorMessage(exception.getMessage());
		repo.save(logDefinition);
	}

	@Async
	@Override
	public void writeLog(String className, String methodName, String message) {
		
		LogDefinition logDefinition = new LogDefinition();
		logDefinition.setClassName(className);
		logDefinition.setMethodName(methodName);
		logDefinition.setMessage(message);
		repo.save(logDefinition);
		
	}

	
	

}
