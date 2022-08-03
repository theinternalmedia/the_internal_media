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

	private static final String EXCEPTION_MESSAGE_DEFAULT = "Có gì đó sai sai, vui lòng thử lại";

	@Autowired
	public GetMessages(MessageSource messageSource) {
		GetMessages.messageSource = messageSource;
	}

	/**
	 * @author minhtuanitk43
	 * @param eMessage
	 * @return message properties by code of eMessage
	 */
	public static String getMessage(ETimMessages eMessage, String... values) {
		return getMessage(eMessage.code, values);
	}

	/**
	 * @author minhtuanitk43
	 * @param code
	 * @return message properties by code
	 */
	public static String getMessage(String code, String... values) {
		String message = messageSource.getMessage(code, values, EXCEPTION_MESSAGE_DEFAULT, null);
		return message;
	}
}
