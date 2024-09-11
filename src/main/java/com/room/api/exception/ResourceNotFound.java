package com.room.api.exception;

import lombok.Data;

@Data
public class ResourceNotFound extends Throwable {

	private static final long serialVersionUID = 1L;
	private String successMessage;
	private String successCode;
	private String failMessage;
	private String failCode;

	public ResourceNotFound(String message, String messageM) {
		super(String.format(messageM, message));
	}

	public ResourceNotFound(String successMessage, String successCode, String failMessage, String failCode) {
		super(String.format(successCode, successMessage, failCode, failMessage));
		this.successMessage = successMessage;
		this.successCode = successCode;
		this.failMessage = failMessage;
		this.failCode = failCode;
	}

}
