package com.tim.data;

public final class TimApiPath {
	public static final String TIM_API = "/api";
	public static final String VER_V1 = "/v1";
	public static final String VER_V2 = "/v2";
	private static final String PAGE_ = "/page";
	private static final String ALL_ = "/all";
	private static final String ADMIN_ = "-admin";
	private static final String TOGGLE_STATUS_ = "/toggle-status";
	private static final String UPLOAD_EXCEL_ = "/upload-excel";
	private static final String UPDATE_AVATAR_ = "/avatar";
	private static final String PATH_VARIABLE_ID = "/{id}";

	public static class Auth {
		private static final String PREFIX = "/auth";
		public static final String LOGIN = PREFIX + "/login";
		public static final String REFRESH_TOKEN = PREFIX + "/refresh-token";
	}

	public static class User {
		private static final String PREFIX_V1 = VER_V1 + "/users";
		public static final String UPDATE_AVATAR = PREFIX_V1 + UPDATE_AVATAR_;
		public static final String UPDATE_USER = PREFIX_V1;
		public static final String GET_BY_USERID = PREFIX_V1;
		public static final String UPDATE_PASSWORD = PREFIX_V1 + "/password";
	}

	public static class Teacher {
		private static final String PREFIX_V1 = VER_V1 + "/teachers";
		public static final String PREFIX_V2 = VER_V2 + "/teachers";
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_;
		public static final String UPLOAD_EXCEL_V2 = PREFIX_V2 + UPLOAD_EXCEL_;
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_USERID = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class Student {
		private static final String PREFIX_V1 = VER_V1 + "/students";
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_;
		public static final String UPLOAD_STUDENT = PREFIX_V1 + "/upload";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_USERID = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class Notification {
		private static final String PREFIX_V1 = VER_V1 + "/notifications";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_ID = PREFIX_V1 + PATH_VARIABLE_ID;
		public static final String GET_BY_SLUG = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;

		public static final String GET_PAGE_ADMIN = PREFIX_V1 + PAGE_ + ADMIN_;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class Faculty {
		private static final String PREFIX_V1 = VER_V1 + "/faculties";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_CODE = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_ALL = PREFIX_V1 + ALL_;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class Class {
		private static final String PREFIX_V1 = VER_V1 + "/classes";
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_;
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_CODE = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class SchoolYear {
		private static final String PREFIX_V1 = VER_V1 + "/school-years";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_CODE = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
		public static final String GET_ALL = PREFIX_V1 + ALL_;
	}

	public static class NotificationGroup {
		private static final String PREFIX_V1 = VER_V1 + "/notification-groups";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_CODE = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_ALL = PREFIX_V1 + ALL_;
	}

	public static class News {
		private static final String PREFIX_V1 = VER_V1 + "/news";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_BY_ID = PREFIX_V1 + PATH_VARIABLE_ID;
		public static final String GET_BY_SLUG = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class Marks {
		public static final String PREFIX_V1 = VER_V1 + "/marks";
		public static final String CREATE = PREFIX_V1;
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_;
		public static final String GET_BY_ID = PREFIX_V1 + PATH_VARIABLE_ID;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class Subject {
		public static final String PREFIX_V1 = VER_V1 + "/subjects";
		public static final String CREATE = PREFIX_V1;
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_;
		public static final String GET_BY_ID = PREFIX_V1 + PATH_VARIABLE_ID;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;
	}

	public static class EducationProgram {
		private static final String PREFIX_V1 = VER_V1 + "/education-program";
		public static final String GET_BY_CODE = PREFIX_V1;
		public static final String GET_SUBJECT_LIST = PREFIX_V1 + "/subjects";
		public static final String CREATE = PREFIX_V1;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_;
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_;
		public static final String GET_PAGE = PREFIX_V1 + PAGE_;

	}
}
