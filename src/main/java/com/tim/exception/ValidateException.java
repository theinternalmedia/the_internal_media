package com.tim.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

import com.tim.data.ETimMessages;

import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Getter
public class ValidateException extends CustomException{

	private static final long serialVersionUID = 8885482666209402062L;

	private List<String> errMsgs = new ArrayList<>();

	public ValidateException(ETimMessages eTimMessages, List<String> errMsgs, String... values) {
		super(eTimMessages, values);
		if(ObjectUtils.isNotEmpty(errMsgs)) {
			this.errMsgs = errMsgs.stream().sorted().collect(Collectors.toList());
		}
	}

}
