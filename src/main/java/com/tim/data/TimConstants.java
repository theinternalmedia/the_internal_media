package com.tim.data;

import java.util.Arrays;
import java.util.List;

public final class TimConstants {
	// App name
	public static final String APP_NAME = "THE_INTERNAL_MEDIA";

	// Admin root
	public static final String ADMIN_USERID = "admin";
	public static final String ADMIN_PASSWORD = "admin@2022";

	// Response
	public static final String SUCCESS = "success.";
	public static final String UNSUCCESS = "unsuccess.";
	
	//
	public static final String YES = "Có";
	public static final String NO = "Không";
	
	// Regex
	public static final String REGEX_PHONE_VN = "(0[3|5|7|8|9])+([0-9]{8})";
	public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String REGEX_CODE = "^[a-zA-Z0-9_-]*$";
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9!@#$%^&*]*$";

	public static final String USER_DOB_FORMAT = "dd-MM-yyyy";
	public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	
	public static final String TRUE_STR = "true";
	public static final String FALSE_STR = "false";
	
	// Resource file name
	public static final String ACTUAL_FIELDNAME_DTO_NAME_FILE = "ActualFieldNames.json";
	public static final String FIELDNAMES_EXPORTCONFIG_NAME_FILE = "FieldNames_ExportConfig.json";
	public static final String ACTUAL_OBJECT_NAME_FILE = "ActualObjectNames.json";
	
	/** Line Number Header Name in Export Excel File */
	public static final String LINE_NUMBER = "STT";


	/**
	 * file path + prefix
	 * @appName the_internal_media
	 *
	 */
	public static class Upload{
		public static final List<String> IMAGE_MIME_TYPE = Arrays.asList("png", "jpeg", "jpg", "PNG", "JPEG", "JPG");
//		public static final List<String> VIDEO_MIME_TYPE = Arrays.asList("mpeg-4", "mpeg-2", "mp4", "flv", "avi");
//		public static final String IMAGE_TYPE = "IMAGE";
//		public static final String VIDEO_TYPE = "VIDEO";
		
		private static final String PHOTOS_DIR = "/photos";
//		private static final String VIDEO_DIR = "/videos";
		private static final String FILES_DIR = "/files";
		
		public static final String THUMBNAIL_DIR = PHOTOS_DIR + "/thumbnail/";
		public static final String AVATAR_DIR = PHOTOS_DIR + "/avatar/";
		public static final String UPLOAD_FILE_DIR = FILES_DIR + "/uploadfiles/";

		public static final String NEWS_PREFIX = "news_";
		public static final String NOTIFICATION_PREFIX = "notify_";
        public static final String TEACHER_PREFIX = "teacher_";
        public static final String STUDENT_PREFIX = "student_";
        public static final String UPLOAD_FILE_PREFIX = "uploadfile_";

        public static final String SAVE_UNSUCCESS = "Save image unseccess";

	}


	/**
	 * Notification Type
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static final class NotificationType {
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
	public static final class Gender {
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
	public static final class ExcelFiledName {
		public static final String TEACHER = "GiaoVien";
		public static final String STUDENT = "SinhVien";
	}

	/**
	 * Type Value to get value from Excel Cell
	 * 
	 * @appName the_internal_media
	 *
	 */
	public static final class FieldType {
		public static final String DOUBLE = "Double";
		public static final String FLOAT = "Float";
		public static final String INTEGER = "Integer";
		public static final String STRING = "String";
		public static final String BOOLEAN = "Boolean";
		public static final String LOCAL_DATE = "LocalDate";
		public static final String LOCAL_DATE_TIME = "LocalDateTime";
		public static final String LONG = "Long";
	}
	
	public static final class ActualEntityName {
		public static final String TEACHER = "Giảng Viên";
		public static final String STUDENT = "Sinh Viên";
		public static final String FACULTY = "Khoa";
		public static final String CLASS = "Lớp";
		public static final String SCHOOL_YEAR = "Khóa";
		public static final String NEWS = "Tin tức";
		public static final String NOTIFICATION = "Thông báo";
	}
	
	public static final class ApiParamExample{
		public static final class News {
			public static final String CREATE_newsRequestDtoJson = 
					"{\"title\":\"String\", "
					+ "\"content\":\"String\", "
					+ "\"shortDescription\":\"String\", "
					+ "\"facultyCodes\":[\"String\"]}";
			public static final String UPDATE_newsRequestDtoJson = 
					"{\"id\":\"Long\", "
					+ "{\"title\":\"String\", "
					+ "\"content\":\"String\", "
					+ "\"shortDescription\":\"String\", "
					+ "\"facultyCodes\":[\"String\"]}";
		}
		
		public static final class Notification {
			public static final String CREATE_notifyRequestDtoJson = 
					"{\"title\":\"String\", "
					+ "\"content\":\"String\", "
					+ "\"shortDescription\":\"String\", "
					+ "\"type\":\"0\", "
					+ "\"notificationGroupCode\":\"String\", "
					+ "\"facultyCodes\":[\"String\"], "
					+ "\"schoolYearCodes\":[\"String\"], "
					+ "\"classCodes\":[\"String\"]}";
			public static final String UPDATE_notifyRequestDtoJson = 
					"{\"id\":\"Long\", "
					+ "{\"title\":\"String\", "
					+ "\"content\":\"String\", "
					+ "\"shortDescription\":\"String\", "
					+ "\"type\":\"0\", "
					+ "\"notificationGroupCode\":\"String\", "
					+ "\"facultyCodes\":[\"String\"], "
					+ "\"schoolYearCodes\":[\"String\"], "
					+ "\"classCodes\":[\"String\"]}";
		}
	}
}
