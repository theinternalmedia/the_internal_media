package com.tim.data;

public enum ETimMessages {
	
	USER_NOT_FOUND("msg.000", "User not found"),
	ENTITY_NOT_FOUND("msg.001", "ENTITY_NOT_FOUND"),
	
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
	VALIDATION_ERR_MESSAGE("msg.400", "vaidation error message"),
	INVALID_EXCEL_FILE("msg.401", "invalid excel file"),
	INVALID_EXCEL_VALUE("msg.402", "invalid excel value"),
	INVALID_OBJECT_VALUE("msg.403", "invalid object value"),
	INVALID_CELL_VALUE("msg.404", "invalid cell value"),
	NULL_CELL_VALUE("msg.405", "null cell value"),
	CONSTRAIN_VAIOLATION_MESSAGE("msg.406", "constrain vaiolation message"),
	INVALID_OBJECT_VALUE_2("msg.407", "invalid object value return value"),
	INVALID_JSON_OBJECT("msg.408", "invalid string json object"),
	ACCESS_DENIED("msg.000", "temporiry message"),

	/* INTERNAL_SYSTEM_ERROR */
	INTERNAL_SYSTEM_ERROR("msg.999", "Internal System Error message");
	
	/**
	 * code of message properties
	 */
	public final String code;
	/**
	 * description of message
	 */
    public final String description;
    ETimMessages (String code, String description) {
        this.code = code;
        this.description = description;
    }
}
