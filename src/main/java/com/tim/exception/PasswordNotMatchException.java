package com.tim.exception;

import com.tim.data.EExceptionMessage;

public class PasswordNotMatchException extends CustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4535334857331524410L;

	public PasswordNotMatchException(EExceptionMessage eExceptionMessage, String value) {
		super(eExceptionMessage, value);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(EExceptionMessage eExceptionMessage) {
		super(eExceptionMessage);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(int code, String message, String value) {
		super(code, message, value);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}



}
