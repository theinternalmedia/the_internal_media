package com.tim.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TimConstants {
	// App name
	public static final String APP_NAME = "THE_INTERNAL_MEDIA";

	// Admin root
	public static final String ADMIN_ROOT = "admin";

	public static final String OK_STATUS = "ok";
	public static final String OK_MESSAGE = "success";
	public static final String FAIL_MESSAGE = "fail";

	public static final List<String> IMAGE_MIME_TYPE = Arrays.asList("png", "jpeg", "jpg", "gif");
	public static final List<String> VIDEO_MIME_TYPE = Arrays.asList("mpeg-4", "mpeg-2", "mp4", "flv", "avi");
	public static final String IMAGE_TYPE = "IMAGE";
	public static final String VIDEO_TYPE = "VIDEO";

	public static final String REGEX_PHONE_VN = "(0[3|5|7|8|9])+([0-9]{8})";
	public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	public static final String USER_DOB_FORMAT = "dd-MM-yyyy";
	public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	
	public static final String TRUE_STR = "true";
	public static final String FALSE_STR = "false";

	/**
	 * Notification Type
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static class NotificationType {
		public static final int TO_ALL = 1;
		public static final int TO_TEACHER = 2;
		public static final int TO_STUDENT = 3;
	}

	/**
	 * HeaderFields Config
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static class ObjectFields {
		public static final String[] TEACHER = { "userId", "name", "gender", "dob", "address", "phone" };
	}
	
	public static class HeaderFields{
		public static final String LINE_NUMBER = "STT";
		public static final String[] TEACHER = { "Mã GV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Điện thoại" };
	}

	/**
	 * Gender
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static class Gender {
		public static final String MALE_STR = "Nam";
		public static final boolean MALE_BOOL = true;
		public static final String FEMALE_STR = "Nữ";
		public static final boolean FEMALE_BOOL = false;
	}

	public static class ExcelFileName {
		public static final String TEACHER = "GiaoVien";
		public static final String STUDENT = "SinhVien";
	}

	public static class FieldType {
		public static final String DOUBLE = "Double";
		public static final String INTEGER = "Integer";
		public static final String STRING = "String";
		public static final String BOOLEAN = "Boolean";
		public static final String LOCAL_DATE = "LocalDate";
		public static final String LOCAL_DATE_TIME = "LocalDateTime";
		public static final String LONG = "Long";
	}
	
	public static final Map<String, String> FIELD_NAME_CLIENT = new HashMap<String, String>();
	static {
		FIELD_NAME_CLIENT.put("userId", "Mã số SV/GV");
		FIELD_NAME_CLIENT.put("address", "Địa chỉ");
		FIELD_NAME_CLIENT.put("phone", "Số điện thoại");
		FIELD_NAME_CLIENT.put("gender", "Giới tính");
		FIELD_NAME_CLIENT.put("email", "Email");
		FIELD_NAME_CLIENT.put("dob", "Ngày sinh");
	}
}
