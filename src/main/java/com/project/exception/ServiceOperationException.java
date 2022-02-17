package com.project.exception;

public class ServiceOperationException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String message;
	protected Error errorCode;
	protected Throwable error;
	
	public ServiceOperationException() {
		
	}

	public ServiceOperationException(String message, Error errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public ServiceOperationException(String message, Error errorCode, Throwable error) {
		this.message = message;
		this.errorCode = errorCode;
		this.error = error;
	}

	public ServiceOperationException(String message) {
		super(message);
	}
	

}
