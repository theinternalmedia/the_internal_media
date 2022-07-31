package com.tim.exception;

import com.tim.data.ETimMessages;
import com.tim.utils.GetMessages;

public class NotFoundException extends CustomException{

	private static final long serialVersionUID = -8412435662229718124L;

	private String field;
	public NotFoundException(ETimMessages eTimMessages) {
		super(eTimMessages);
		// TODO Auto-generated constructor stub
	}
	public NotFoundException(ETimMessages eTimMessages, String field) {
		super(eTimMessages);
		this.field = field;
		this.setMessage(GetMessages.getMessage(eTimMessages.code, field));
	}
	public String getField() {
		return field;
	}
}
