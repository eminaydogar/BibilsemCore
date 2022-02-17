package com.project.exception;

public class EntityValidationException extends ServiceOperationException {

	private static final long serialVersionUID = 2L;

	public EntityValidationException(String message) {
		super(message);
	}

}
