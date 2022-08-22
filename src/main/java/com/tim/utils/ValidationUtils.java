package com.tim.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.annotation.Phone;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.exception.GlobalExceptionHandler;
import com.tim.exception.ValidateException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @appName the_internal_media
 *
 */
public class ValidationUtils {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Get Message Validate Field
	 * 
	 * @author minhtuanitk43
	 * @param field     Field need to validate
	 * @param fieldName Actual fieldName response to Client
	 * @param value     value of 'field'
	 * @return Error Message if have any validated error, otherwise return NULL
	 */
	public static String getValidateFieldMessage(final Field field, final String fieldName, final Object value) {
		field.setAccessible(true);
		if (field.isAnnotationPresent(Size.class)) {
			Size size = field.getAnnotation(Size.class);
			int max = size.max();
			int min = size.min();

			if (((String) value).length() > max) {
				return "'" + fieldName + "' không quá " + max + " kí tự.";
			}
			if (((String) value).length() < min) {
				return "'" + fieldName + "' không nhỏ hơn " + min + " ký tự.";
			}
		}
		if (field.isAnnotationPresent(NotBlank.class)) {
			if (StringUtils.isBlank((String) value)) {
				return "'" + fieldName + "' không được để trống.";
			}
		}
		if (field.isAnnotationPresent(Email.class)) {
			if (!((String) value).matches(TimConstants.REGEX_EMAIL)) {
				return "'" + fieldName + "' không đúng định dạng.";
			}
		}
		if (field.isAnnotationPresent(Min.class)) {
			Min min = field.getAnnotation(Min.class);
			if ((long) value < min.value()) {
				return "'" + fieldName + "' phải lớn hơn " + min.value() + ".";
			}
		}
		if (field.isAnnotationPresent(Max.class)) {
			Max max = field.getAnnotation(Max.class);
			if ((long) value > max.value()) {
				return "'" + fieldName + "' phải nhỏ hơn " + max.value() + ".";
			}
		} else if (field.isAnnotationPresent(NotNull.class)) {
			if (value == null) {
				return "'" + fieldName + "' không được để trống.";
			}
		} else if (field.isAnnotationPresent(Phone.class)) {
			if (!((String) value).matches(TimConstants.REGEX_PHONE_VN)) {
				return "'" + fieldName + "' không đúng định dạng.";
			}
		}
		return null;
	}

	/**
	 * Validate Object
	 * 
	 * @author minhtuanitk43
	 * @param object need to validate
	 */
	public static void validateObject(final Object object) {
		final String onjectClassName = "Object";

		// Declare list Error Message
		List<String> errMessages = new ArrayList<>();

		// Get class of object
		Class<?> objectClass = object.getClass();

		// Get actual name of object
		final String simpleName = objectClass.getSimpleName();

		// Get class fields
		Field[] fields = objectClass.getDeclaredFields();
		Map<String, String> actualFieldNames = new HashMap<String, String>();
		Map<String, String> actFieldNames = null;
		while (objectClass != null && !objectClass.getSimpleName().equals(onjectClassName)) {
			actFieldNames = Utility.getObjectFromJsonFile(TimConstants.ACTUAL_FIELDNAME_DTO_NAME_FILE,
					new TypeReference<Map<String, Map<String, String>>>() {
					}).get(objectClass.getSimpleName());
			if (actFieldNames != null) {
				actualFieldNames.putAll(actFieldNames);
			}
			fields = ArrayUtils.addAll(objectClass.getDeclaredFields());
			objectClass = objectClass.getSuperclass();
		}

		// Get actual object name response to Client from file json config
		String actualOjectName = Utility
				.getObjectFromJsonFile(TimConstants.ACTUAL_OBJECT_NAME_FILE, new TypeReference<Map<String, String>>() {
				}).get(simpleName);
		if (actualOjectName == null) {
			actualOjectName = simpleName;
		}

		// Loop to validate fields
		String fieldName = null;
		for (Field field : fields) {
			if (field.getAnnotations().length == 0) {
				continue;
			}
			field.setAccessible(true);
			try {
				fieldName = actualFieldNames.get(field.getName());
				String mes = getValidateFieldMessage(field, fieldName != null ? fieldName : field.getName(),
						field.get(object));
				if (mes != null) {
					errMessages.add(mes);
				}
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				throw new ValidateException(ETimMessages.INVALID_OBJECT_VALUE, null, actualOjectName);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				throw new ValidateException(ETimMessages.INVALID_OBJECT_VALUE, null, actualOjectName);
			}
		}
		// If Error Message not empty, throw ValidateException
		if (errMessages.size() > 0) {
			throw new ValidateException(ETimMessages.INVALID_OBJECT_VALUE, errMessages, actualOjectName);
		}
	}

	/**
	 * Validate Value if is Instant Of Object
	 * 
	 * @author minhtuanitk43
	 * @param <T>
	 * @param value
	 * @param clazz
	 * @param fieldName
	 * @return value if validation is success, otherwise ValidateException will be throw
	 */
	public static <T> T getValue(Object value, Class<T> clazz, String fieldName) {
		try {
			return clazz.cast(value);
		} catch (Exception e) {
			throw new ValidateException(ETimMessages.INVALID_OBJECT_VALUE_2, null, 
					StringUtils.isBlank(fieldName) ? "Dữ liệu" : fieldName,
							String.valueOf(value));
		}
	}

	public static void validateImage(MultipartFile image){
		String imageExtension = FilenameUtils.getExtension(image.getOriginalFilename());
		if(!TimConstants.IMAGE_MIME_TYPE.contains(imageExtension)){
			throw new MultipartException("Not image");
		}
	}
}
