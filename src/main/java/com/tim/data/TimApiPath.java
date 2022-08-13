package com.tim.data;

public final class TimApiPath {
	public static final String TIM_API_PREFIX = "/api/v1";
	
	public static class Auth{
		public static final String PREFIX = TIM_API_PREFIX + "/auth";
		public static final String LOGIN = "/login";
	}
	
	public static class Teacher{
		public static final String PREFIX = TIM_API_PREFIX + "/teacher";
		public static final String SAVE = "/create";
		public static final String UPLOAD_EXCEL = "/upload-excel";
		public static final String GET = "/get";
	}

	public static class Student{
		public static final String PREFIX = TIM_API_PREFIX + "/student";
		public static final String SAVE = "/create";
		public static final String UPLOAD_EXCEL = "/upload-excel";
		public static final String GET = "/get";
	}

	public static class NotificationGroup{
		public static final String PREFIX = TIM_API_PREFIX + "/notificationgroup";
		public static final String SAVE = "/create";
		public static final String UPLOAD_EXCEL = "/upload-excel";
		public static final String GET = "/get";
	}

	public static class News{
		public static final String PREFIX = TIM_API_PREFIX + "/news";
		public static final String SAVE = "/create";
		public static final String SCHOOL = "/school";
		public static final String FACULTY = "/faculty";
		public static final String UPDATE = "/update";
		public static final String UPLOAD_EXCEL = "/upload-excel";
		public static final String GET = "/get";
		public static final String DELETE = "/delete";
	}
}
