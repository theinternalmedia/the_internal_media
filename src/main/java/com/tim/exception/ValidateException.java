package com.tim.exception;

import java.util.List;

import com.tim.data.ETimMessages;

import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Getter
public class ValidateException extends TimException{

	private static final long serialVersionUID = 8885482666209402062L;

	public ValidateException(ETimMessages eTimMessages, List<String> errMsgs, String... values) {
		super(errMsgs, eTimMessages, values);
		// TODO Auto-generated constructor stub
	}

	public ValidateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
