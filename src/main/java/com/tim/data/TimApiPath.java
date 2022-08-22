package com.tim.data;

public final class TimApiPath {
	public static final String TIM_API = "/api";
	public static final String VER_V1 = "/v1";
	public static final String VER_V2 = "/v2";
	public static final String TOGGLE_STATUS_STR = "/toggle-status";
	public static final String UPLOAD_EXCEL_STR = "/upload-excel";
	public static final String PATH_VARIABLE_CODE = "/{code}";
	public static final String PATH_VARIABLE_ID = "/{id}";

	public static class Auth {
		public static final String PREFIX = "/auth";
		public static final String LOGIN = PREFIX + "/login";
	}

	public static class Teacher {
		private static final String PREFIX_V1 = VER_V1 + "/teachers";
		public static final String PREFIX_V2 = VER_V2 + "/teachers";
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_STR;
		public static final String UPLOAD_EXCEL_V2 = PREFIX_V2 + UPLOAD_EXCEL_STR;
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_CODE;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1;
	}

	public static class Student {
		private static final String PREFIX_V1 = VER_V1 + "/students";
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_STR;
		public static final String UPLOAD_STUDENT = PREFIX_V1 + "/upload";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_CODE;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
	}

	public static class Notification {
		private static final String PREFIX_V1 = VER_V1 + "/notifications";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_ID;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
		
		public static final String GET_PAGE = PREFIX_V1;
	}

	public static class Faculty {
		private static final String PREFIX_V1 = VER_V1 + "/faculties";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_CODE;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
	}

	public static class Class {
		private static final String PREFIX_V1 = VER_V1 + "/classes";
		public static final String UPLOAD_EXCEL = PREFIX_V1 + UPLOAD_EXCEL_STR;
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_CODE;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
	}

	public static class SchoolYear {
		private static final String PREFIX_V1 = VER_V1 + "/school-years";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_CODE;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
	}

	public static class NotificationGroup{
		public static final String PREFIX_V1 = VER_V1 + "/notification-groups";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_CODE;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
	}

	public static class News{
		public static final String PREFIX_V1 = VER_V1 + "/news";
		public static final String CREATE = PREFIX_V1;
		public static final String GET_ONE = PREFIX_V1 + PATH_VARIABLE_ID;
		public static final String UPDATE = PREFIX_V1;
		public static final String TOGGLE_STATUS = PREFIX_V1 + TOGGLE_STATUS_STR;
		public static final String DELETE = PREFIX_V1;
		public static final String GET_PAGE = PREFIX_V1;
	}
}
