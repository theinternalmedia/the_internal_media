package com.tim.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.tim.data.ETimMessages;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class GetMessages {
	private static MessageSource messageSource;

	private static final String DEFAUL_EXCEPTION_MESSAGE = "Có gì đó sai sai, vui lòng thử lại";

	@Autowired
	public GetMessages(MessageSource messageSource) {
		GetMessages.messageSource = messageSource;
	}

	/**
	 * @author minhtuanitk43
	 * @param eMessage
	 * @return message properties by code of eMessage
	 */
	public static String getMessage(ETimMessages eMessage) {
		return messageSource.getMessage(eMessage.code, null, DEFAUL_EXCEPTION_MESSAGE, null);
	}
}
