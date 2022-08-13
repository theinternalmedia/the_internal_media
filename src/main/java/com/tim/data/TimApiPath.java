package com.tim.data;

public final class TimApiPath {
	public static final String TIM_API_PREFIX = "/api/v1";
	
	public static class Auth{
		public static final String PREFIX = TIM_API_PREFIX + "/auth";
		public static final String LOGIN = "/login";
	}
	
	public static class Teacher{
		public static final String PREFIX = TIM_API_PREFIX + "/teacher";
		public static final String SAVE = "/save";
		public static final String UPLOAD_EXCEL = "/upload-excel";
		public static final String GET = "/get";
	}
	
	public static class Class {
		public static final String PREFIX = TIM_API_PREFIX + "/class";
		public static final String SAVE = "/save";
		public static final String UPDATE = "/update";
		public static final String GET = "/get";
		
	}
}
