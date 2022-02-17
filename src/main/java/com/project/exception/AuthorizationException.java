package com.project.exception;

public class AuthorizationException extends ServiceOperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	public AuthorizationException(String message) {
		super(message);
	}
	public AuthorizationException(String message,Error errorCode) {
		super(message,errorCode);
	}

}
