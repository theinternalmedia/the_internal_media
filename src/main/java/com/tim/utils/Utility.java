package com.tim.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
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
	
	private static ObjectMapper objectMapper = new ObjectMapper();

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
		try {
			String jsonConfig = new String(
					Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(fileName).toURI())));
			t = convertStringJsonToObject(jsonConfig, typeReference);
		} catch (IOException | URISyntaxException e) {
			logger.error("Cannot read file json: {}", fileName);
			e.printStackTrace();
			throw new CustomException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}
		return t;
	}
	
	public static <T> T convertStringJsonToObject(final String stringJson, final TypeReference<T> typeReference) {
		T t = null;
		try {
			t = objectMapper.readValue(stringJson, typeReference);
		} catch (JsonProcessingException e) {
			String typeName = typeReference.getType().getTypeName();
			logger.error("Cannot convert stringJson to object: {}", typeName);
			e.printStackTrace();
			throw new CustomException(ETimMessages.INVALID_JSON_OBJECT, typeName);
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
	
	/**
	 * @author minhtuanitk43
	 * @param key 
	 * @return Actual Object Name (classSimpleName)
	 */
	public static String getActualObjectName(String key) {
		return getObjectFromJsonFile(TimConstants.ACTUAL_OBJECT_NAME_FILE, new TypeReference<Map<String, String>>() {
		}).get(key);
	}
}
