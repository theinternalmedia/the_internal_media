package com.tim.exception;

import com.tim.data.ETimMessages;

/**
 * 
 * @appName the_internal_media
 *
 */
public class PasswordNotMatchException extends CustomException {

	private static final long serialVersionUID = 4535334857331524410L;

	public PasswordNotMatchException(ETimMessages eTimMessages, String value) {
		super(eTimMessages, value);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(ETimMessages eTimMessages) {
		super(eTimMessages);
		// TODO Auto-generated constructor stub
	}

}
