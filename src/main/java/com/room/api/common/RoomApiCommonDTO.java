package com.room.api.common;

public class RoomApiCommonDTO {

	private String successMessage;
	private String successCode;
	private String failMessage;
	private String failCode;
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}
	public String getFailMessage() {
		return failMessage;
	}
	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}
	
	public String getFailCode() {
		return failCode;
	}
	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}
	public RoomApiCommonDTO(String successMessage, String successCode, String failMessage, String failCode) {
		super();
		this.successMessage = successMessage;
		this.successCode = successCode;
		this.failMessage = failMessage;
		this.failCode = failCode;
	}
	public RoomApiCommonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RoomApiCommonDTO [successMessage=" + successMessage + ", successCode=" + successCode + ", failMessage="
				+ failMessage + ", failCode=" + failCode + "]";
	}
	
	

}
