package com.tim.data;

import java.util.Arrays;
import java.util.List;

public final class TimSystemConstants {
	public static final String APP_NAME = "THE_INTERNAL_MEDIA";
	
	public static final String ADMIN_ROOT = "admin";

	public static final List<String> IMAGE_MIME_TYPE = Arrays.asList("png", "jpeg", "jpg", "gif");
	public static final List<String> VIDEO_MIME_TYPE = Arrays.asList("mpeg-4", "mpeg-2", "mp4", "flv", "avi");
	public static final String IMAGE_TYPE = "IMAGE";
	public static final String VIDEO_TYPE = "VIDEO";

	public static final String REGEX_PHONE_VN = "(0[3|5|7|8|9])+([0-9]{8})";
	public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


}
