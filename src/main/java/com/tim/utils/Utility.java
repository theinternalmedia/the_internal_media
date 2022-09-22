package com.tim.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Date;
import java.util.Map;

import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.exception.TimException;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class Utility {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(Utility.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

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
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream is = cl.getResourceAsStream(fileName);
			String jsonConfig = IOUtils.toString(is, CharsetNames.UTF_8); 
			t = convertStringJsonToObject(jsonConfig, typeReference);
		} catch (IOException e) {
			logger.error("Cannot read file json: {}", fileName);
			e.printStackTrace();
			throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}
		return t;
	}
	
	/**
	 * Convert String JSon To Object.
	 * 
	 * @author minhtuanitk43
	 * @param <T>
	 * @param stringJson
	 * @param typeReference
	 * @return Object <T> get from String Json
	 */
	public static <T> T convertStringJsonToObject(final String stringJson, 
			final TypeReference<T> typeReference) {
		T t = null;
		try {
			t = objectMapper.readValue(stringJson, typeReference);
		} catch (JsonProcessingException e) {
			String typeName = typeReference.getType().getTypeName();
			logger.error("Cannot convert stringJson to object: {}", typeName);
			e.printStackTrace();
			throw new TimException(ETimMessages.VALIDATION_ERR_MESSAGE);
		}
		return t;
	}

	/**
	 * @author minhtuanitk43
	 * @param key Object class name
	 * @return Actual Object Name (classSimpleName)
	 */
	public static String getActualObjectName(String key) {
		return getObjectFromJsonFile(TimConstants.ACTUAL_OBJECT_NAME_FILE, 
				new TypeReference<Map<String, String>>() {
		}).get(key);
	}
	/**
	 * Get slugs
	 * 
	 * @author minhtuanitk43
	 * @param string
	 * @return
	 */
	public static String generateSlugs (String string) {
		string = createSlugs(string);
		return string.trim().replaceAll(" +", "-");
	}
	
	/**
	 * Format filename
	 * 
	 * @author minhtuanitk43
	 * @param fileName
	 * @return
	 */
	public static String formatFileName(String fileName) {
		fileName = createSlugs(fileName);
		return fileName.trim().replaceAll(" +", "_");
	}
	/**
	 * @author tuan_nguyen
	 * @param string
	 * @return
	 */
	private static String createSlugs(String string) {
		string = unaccent(string);

		StringBuilder tem = new StringBuilder();
		for (int i = 0; i < string.length(); ++i) {
			if (string.charAt(i) >= 'a' && string.charAt(i) <= 'z'  
					|| string.charAt(i) >= 'A' && string.charAt(i) <= 'Z' 
							||Character.isDigit(string.charAt(i))) {
				tem.append(string.charAt(i));
			} else {
				tem.append(' ');
			}
		}
		return tem.toString();
	}
	
	/**
	 * @author tuan_nguyen
	 * @param src
	 * @return
	 */
	private static String unaccent(String src) {
		return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Get fileName from current time
	 * 
	 * @author minhtuanitk43
	 * @param newsPrefix
	 * @return
	 */
	public static String getFileNameFromTime(String prefixName) {
		return prefixName + new Date().getTime();
	}
}
