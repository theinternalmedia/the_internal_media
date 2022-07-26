package com.tim.exception;

import com.tim.data.EExceptionMessage;

public class CustomException extends RuntimeException{

	/**
	 * Triệu Minh Tuấn
	 */
	private static final long serialVersionUID = 6795403562971795071L;
	private int code;
	private String message;
	private String value;
	
	public CustomException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public CustomException(int code, String message, String value) {
		this.code = code;
		this.message = message;
		this.value = value;
	}
	public CustomException(EExceptionMessage eExceptionMessage) {
		this.code = eExceptionMessage.code;
		this.message = eExceptionMessage.message;
	}
	public CustomException(EExceptionMessage eExceptionMessage, String value) {
		this.code = eExceptionMessage.code;
		this.message = eExceptionMessage.message;
		this.value = value;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
