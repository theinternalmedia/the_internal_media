package com.tim.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

import com.tim.data.ETimMessages;
import com.tim.utils.MessageUtils;

/**
 * 
 * @appName the_internal_media
 *
 */
public class TimException extends RuntimeException {

	private static final long serialVersionUID = 6795403562971795071L;
	
	protected String message;
	
	private List<String> errMsgs = new ArrayList<String>();

	public TimException() {}
	
	public TimException(String message) {
		this.message = message;
	}
	
	/**
	 *  
	 * @param eTimMessages ETimMessages
	 * @param errMsgs Message Details List
	 * @param values
	 */
	public TimException(List<String> errMsgs, ETimMessages eTimMessages, String...values) {
		this.message = MessageUtils.get(eTimMessages, values);
		if(ObjectUtils.isNotEmpty(errMsgs)) {
			this.errMsgs = errMsgs.stream().sorted().collect(Collectors.toList());
		}
	}
	
	/**
	 * 
	 * @param eTimMessages ETimMessages
	 * @param values values
	 */
	public TimException(ETimMessages eTimMessages, String...values) {
		this.message = MessageUtils.get(eTimMessages, values);
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrMsgs() {
		return errMsgs;
	}
}
