package com.tim.utils;

import org.apache.commons.lang3.ObjectUtils;
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
public class MessageUtils {

	private static MessageSource messageSource;

	private static final String EXCEPTION_MESSAGE_DEFAULT = "Có gì đó sai sai, vui lòng kiểm tra và thử lại";
	
	/**
	 * Constructor inject Beans
	 * 
	 * @param messageSource
	 */
	@Autowired
	public MessageUtils(MessageSource messageSource) {
		MessageUtils.messageSource = messageSource;
	}
	
	/**
	 * Get message.
	 * 
	 * @author minhtuanitk43
	 * @param eMessage
	 * @return message properties by code of eMessage
	 */
	public static String get(ETimMessages eMessage, String... values) {
		if (ObjectUtils.isEmpty(values)) {
			return eMessage.message;
		}
		return getMessage(eMessage.code, values);
	}

	/**
	 * @author minhtuanitk43
	 * @param code
	 * @return message properties by code
	 */
	private static String getMessage(String code, String... values) {
		String message = messageSource.getMessage(code, values, EXCEPTION_MESSAGE_DEFAULT, null);
		return message;
	}
}
