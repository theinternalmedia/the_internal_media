package com.tim.data;

import java.util.Arrays;
import java.util.List;

public final class TimConstants {
	// App name
	public static final String APP_NAME = "THE_INTERNAL_MEDIA";

	// Admin root
	public static final String ADMIN_ROOT = "admin";

	// Response
	public static final String OK_STATUS = "ok";
	public static final String NOT_OK_STATUS = "not ok";
	public static final String OK_MESSAGE = "success.";
	public static final String NOT_OK_MESSAGE = "unsuccess.";
	public static final String DATA_EMPTY_MESSAGE = "data is empty.";

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
	
	public static final String ACTUAL_FIELDNAME_DTO_NAME_FILE = "ActualFieldNames.json";
	public static final String FIELDNAMES_EXPORTCONFIG_NAME_FILE = "FieldNames_ExportConfig.json";
	public static final String ACTUAL_OBJECT_NAME_FILE = "ActualObjectNames.json";
	
	/** Line Number Header Name in Export Excel File */
	public static final String LINE_NUMBER = "STT";
	

	/**
	 * Notification Type
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static class NotificationType {
		public static final int TO_ALL = 0;
		public static final int TO_TEACHER = 1;
		public static final int TO_STUDENT = 2;
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

	/**
	 * Excel file name be exported
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static class ExcelFiledName {
		public static final String TEACHER = "GiaoVien";
		public static final String STUDENT = "SinhVien";
	}

	/**
	 * Type Value to get value from Excel Cell
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static class FieldType {
		public static final String DOUBLE = "Double";
		public static final String INTEGER = "Integer";
		public static final String STRING = "String";
		public static final String BOOLEAN = "Boolean";
		public static final String LOCAL_DATE = "LocalDate";
		public static final String LOCAL_DATE_TIME = "LocalDateTime";
		public static final String LONG = "Long";
	}
	
	public static class ActualEntityName {
		public static final String TEACHER = "Giáo Viên";
		public static final String STUDENT = "Sinh Viên";
		public static final String FACULTY = "Khoa";
		public static final String CLASS = "Lớp";
		public static final String SCHOOL_YEAR = "Khóa";
		public static final String NEWS = "Tin tức";
	}
}
