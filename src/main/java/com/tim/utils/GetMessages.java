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
	
	private static final String DEFAUL_MESSAGE = "Lỗi không xác định";
	private static final String DEFAUL_VALIDATE_MESSAGE = "Dữ liệu nhập vào không hợp lệ";
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
		if(eMessage.code.startsWith("E")) {
			return messageSource.getMessage(eMessage.code, null, DEFAUL_EXCEPTION_MESSAGE, null);
		}else if(eMessage.code.startsWith("V")){
			return messageSource.getMessage(eMessage.code, null, DEFAUL_VALIDATE_MESSAGE, null);
		}
		return messageSource.getMessage(eMessage.code, null, DEFAUL_MESSAGE, null);
	}
}
