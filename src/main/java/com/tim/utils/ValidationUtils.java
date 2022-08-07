package com.tim.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.tim.annotation.Phone;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.exception.GlobalExceptionHandler;
import com.tim.exception.ValidateException;

public class ValidationUtils {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public static String validateField(Field field, String fieldName, Object value) {
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

	public static void validateObject(Object object) {
		List<String> errMessages = new ArrayList<>();
		Class<?> objectClass = object.getClass();
		Class<?> parent = objectClass.getSuperclass();
		Field[] fields = ArrayUtils.addAll(parent.getDeclaredFields(), objectClass.getDeclaredFields());
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				String mes = validateField(field, 
						TimConstants.FIELD_NAME_CLIENT.get(field.getName()), field.get(object));
				if (mes != null) {
					errMessages.add(mes);
				}
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				throw new ValidateException(ETimMessages.INVALID_EXCEL_VALUE, null, objectClass.getSimpleName());
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				throw new ValidateException(ETimMessages.INVALID_EXCEL_VALUE, null, objectClass.getSimpleName());
			}
		}
		if (errMessages.size() > 0) {
			throw new ValidateException(ETimMessages.INVALID_EXCEL_VALUE, errMessages, objectClass.getSimpleName());
		}
	}

}
