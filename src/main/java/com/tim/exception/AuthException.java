package com.tim.exception;

import com.tim.data.ETimMessages;

/**
 * 
 * @appName the_internal_media
 *
 */
public class AuthException extends CustomException {

	private static final long serialVersionUID = -5215860878746742907L;

	public AuthException(ETimMessages eTimMessages) {
		super(eTimMessages);
		// TODO Auto-generated constructor stub
	}
	
	public AuthException(ETimMessages eTimMessages, String value) {
		super(eTimMessages, value);
		// TODO Auto-generated constructor stub
	}
	
}
