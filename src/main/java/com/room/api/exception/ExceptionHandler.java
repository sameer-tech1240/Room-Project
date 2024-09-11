package com.room.api.exception;

import com.room.api.model.EmployeeDTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionHandler extends EmployeeDTO{
	
	private String successMessage;
	private String successCode;
	private String failMessage;
	private String failCode;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
      
	public ExceptionHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ExceptionHandler(String successMessage, String successCode, String failMessage, String failCode) {
		super();
		this.successMessage = successMessage;
		this.successCode = successCode;
		this.failMessage = failMessage;
		this.failCode = failCode;
	}


	
}
