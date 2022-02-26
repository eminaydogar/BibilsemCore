package com.project.entity;

import java.util.Date;

import com.project.cache.BBConstant.OperationStatus;
import com.project.exception.ServiceOperationException;

import lombok.Data;

@Data
public class BBResponse<T> {
	private int status;
	private String message;
	private Date time;
	private T response;

	public BBResponse() {}

	public BBResponse<T> handle(T response, int status, Exception exception) {
		this.response = response;
		this.status = status;
		this.message = exception.getMessage();
		return this;
	}

	public void setFaildResponse(Exception e) {
		if (e instanceof ServiceOperationException) {
			this.message = e.getMessage();
		}
		this.status = OperationStatus.ERROR;
		this.time=new Date();
		this.response = null;

	}

	public void setSuccessResponse(T response) {
		this.status = OperationStatus.SUCCESS;
		this.message = "İŞLEM BAŞARILI";
		this.time=new Date();
		this.response = response;

	}

	public void clear() {
		status = 0;
		message = null;
		response = null;
	}

}
