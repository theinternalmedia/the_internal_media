package com.tim.data;

public enum ETimMessages {
	
	/** Thông tin tài khoản không chính xác. */
	USER_NOT_FOUND("msg.000", "Thông tin tài khoản không chính xác."),
	/** Không tìm thấy {0} với {1} = {2}. */
	ENTITY_NOT_FOUND("msg.001", "Không tìm thấy thực thể."),
	/** Expired Token: {0}. */
	EXPIRED_TOKEN("msg.002", "expired token."),
	/** Invalid Token: {0}. */
	INVALID_TOKEN("msg.003", "invalid token."),
	/** {0} đã tồn tại: {1}. */
	ALREADY_EXISTS("msg.004", "Thực thể đã tồn tại."),
	/** Mật khẩu không chính xác*/
	PASSWORD_NOT_MATCH("msg.005", "Mật khẩu không đúng"),
//	INVALID_SHEET_NAME("msg.004", "invalid sheet name"),
//	USER_NAME_ALREADY_EXISTS("msg.002", "Tài khoản đã tồn tại"),
//	EMAIL_ALREADY_EXISTS("msg.003", "Email đã tồn tại"),
//	PASSWORD_NOT_MATCH("msg.004", "Mật khẩu không đúng"),
//	PASSWORD_NOT_INVALID("msg.005", "Mật khẩu không hợp lệ"),
//	CAN_NOT_CHANGE_ADMIN_ROOT("msg.006", "Can not change ADMIN ROOT"),
//	
//	/*Role exception message*/
//	CAN_NOT_CHANGE_SYSTEM_ROLE("msg.007", "Can not change SYSTEM ROLE"),
//	CAN_NOT_DELETE_SYSTEM_ROLE("msg.008", "Can not delete SYSTEM ROLE"),
//	ROLE_NAME_ALREADY_EXIST("msg.009", "Tên ROLE đã tồn tại !"),
//	
//	
//	/*File Exception*/
//	
//	TYPE_FILE_INVALID("msg.010", "Type file only are: image/video"),
//	FILE_IS_NULL("msg.011", "File must be not null"),
//	IS_NOT_AUTHOR_OR_ADMIN("msg.012", "Chỉ có tác giả/admin mới có thể xóa"),
//	
//	EMAIL_USERNAME_NOT_FOUND("msg.013", " not found"),
	
	/* ========== VALIDATION MESSAGE =================*/
	/** Dữ liệu không hợp lệ. */
	VALIDATION_ERR_MESSAGE("msg.400", "Dữ liệu không hợp lệ."),
	/** Định dạng tệp tin phải là Excel. */
	INVALID_EXCEL_FILE("msg.401", "Định dạng tệp tin phải là Excel."),
	/** Danh sách {0} vừa nhập vào không đúng tại các ô. */
	INVALID_EXCEL_VALUE("msg.402", "Danh sách vừa nhập vào không đúng tại các ô."),
	/** {0} vừa nhập vào không hợp lệ. */
	INVALID_OBJECT_VALUE("msg.403", "Dữ liệu vừa nhập vào không hợp lệ"),
	/** {0}: Kiểu dữ liệu không đúng định dạng. */
	INVALID_CELL_VALUE("msg.404", "Kiểu dữ liệu không đúng định dạng."),
	/** {0}: Dữ liệu trống. */
	NULL_CELL_VALUE("msg.405", "Dữ liệu trống."),
	/** File hình ảnh không hợp lệ: {0}. */
	INVALID_IMAGE_FILE("msg.406", "File hình ảnh không hợp lệ."),
	/** {0} vừa nhập vào không hợp lệ: {1}. */
	INVALID_OBJECT_VALUE_2("msg.407", "Dữ liệu vừa nhập vào không hợp lệ."),
//	INVALID_JSON_OBJECT("msg.408", "invalid string json object"),
	
	ACCESS_DENIED("msg.000", "temporiry message"),

	/* INTERNAL_SYSTEM_ERROR */
	/** Lỗi hệ thống, vui lòng kiểm tra và thử lại. */
	INTERNAL_SYSTEM_ERROR("msg.999", "Lỗi hệ thống, vui lòng kiểm tra và thử lại.");
	
	/**
	 * code of message properties
	 */
	public final String code;
	/**
	 * message
	 */
    public final String message;
    ETimMessages (String code, String message) {
        this.code = code;
        this.message = message;
    }
}
