//package com.tim.utils;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//
//import com.tim.dto.BaseDto;
//import com.tim.exception.GlobalExceptionHandler;
//
//public class ValidationUtils {
//	private static Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);
//	public static <T extends BaseDto> void validate(T object) {
//		List<String> messages = new ArrayList<String>();
//		Class<?> objectClass = object.getClass();
//		for (Field field : objectClass.getFields()) {
//			Annotation annotation = field.getAnnotation(NotNull.class);
//			if ( annotation != null) {
//				try {
//					String strObject = (String) field.get(object);
//					if (StringUtils.isBlank(strObject)) {
//						messages.add(((NotNull) annotation).message());
//					}
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					logger.error(e.getMessage());
//					e.printStackTrace();
//				}
//			}
//			if (field.getAnnotation(Size.class) != null) {
//				try {
//					String strObject = (String) field.get(object);
//					if (strObject) {
//						messages.add(field.getAnnotation(NotNull.class).message());
//					}
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					logger.error(e.getMessage());
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
//
//	public boolean doesObjectContainField(Object object, String fieldName) {
//		Class<?> objectClass = object.getClass();
//		for (Field field : objectClass.getFields()) {
//			if (field.getName().equals(fieldName)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//}
