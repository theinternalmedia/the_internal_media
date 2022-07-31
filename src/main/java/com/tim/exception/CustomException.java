package com.tim.exception;

import com.tim.data.ETimMessages;
import com.tim.utils.GetMessages;

/**
 * 
 * @appName the_internal_media
 *
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 6795403562971795071L;
	private String code;
	private String message;
	
	public CustomException(ETimMessages eTimMessages) {
		this.code = eTimMessages.code;
		this.message = GetMessages.getMessage(eTimMessages.code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
