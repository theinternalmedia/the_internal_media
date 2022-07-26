package com.tim.exception;

import com.tim.data.EExceptionMessage;

public class AuthException extends CustomException {
	/**
	 * Triệu Minh Tuấn
	 */
	private static final long serialVersionUID = -5215860878746742907L;

	public AuthException(EExceptionMessage eExceptionMessage, String value) {
		super(eExceptionMessage, value);
		// TODO Auto-generated constructor stub
	}
	public AuthException(int code, String message, String value) {
		super(code, message, value);
		// TODO Auto-generated constructor stub
	}

	public AuthException(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}
	
}
