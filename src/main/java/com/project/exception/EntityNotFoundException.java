package com.project.exception;

public class EntityNotFoundException extends ServiceOperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EntityNotFoundException() {
		this.message="Entity not found";
	}

}
