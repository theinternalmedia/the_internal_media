package com.tim.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.data.ETimMessages;
import com.tim.exception.CustomException;
import com.tim.exception.GlobalExceptionHandler;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class Utility {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private static MessageSource messageSource;

	private static final String EXCEPTION_MESSAGE_DEFAULT = "Có gì đó sai sai, vui lòng thử lại";

	/**
	 * Constructor inject Beans
	 * 
	 * @param messageSource
	 */
	@Autowired
	public Utility(MessageSource messageSource) {
		Utility.messageSource = messageSource;
	}

	/**
	 * GetObjectFromJsonFile.
	 * 
	 * @author minhtuanitk43
	 * @param <T>           Object Type want to get from json file
	 * @param fileName      name of json file
	 * @param typeReference Type to get from json file
	 * @return <T> object get from json file
	 */
	public static <T> T getObjectFromJsonFile(final String fileName, final TypeReference<T> typeReference) {
		T t = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonConfig = new String(
					Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(fileName).toURI())));
			t = objectMapper.readValue(jsonConfig, typeReference);
		} catch (IOException | URISyntaxException e) {
			logger.error("Cannot read file json: {}", fileName);
			e.printStackTrace();
			throw new CustomException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}
		return t;
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
	private static String getMessage(String code, String... values) {
		String message = messageSource.getMessage(code, values, EXCEPTION_MESSAGE_DEFAULT, null);
		return message;
	}
}
