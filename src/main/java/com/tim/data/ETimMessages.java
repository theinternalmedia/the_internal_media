package com.tim.data;

public enum ETimMessages {
	/*User exception message*/
	USER_NOT_FOUND("msg.001", "Tài khoản không tồn tại"),
	USER_NAME_ALREADY_EXISTS("msg.002", "Tài khoản đã tồn tại"),
	EMAIL_ALREADY_EXISTS("msg.003", "Email đã tồn tại"),
	PASSWORD_NOT_MATCH("msg.004", "Mật khẩu không đúng"),
	PASSWORD_NOT_INVALID("msg.005", "Mật khẩu không hợp lệ"),
	CAN_NOT_CHANGE_ADMIN_ROOT("msg.006", "Can not change ADMIN ROOT"),
	
	/*Role exception message*/
	CAN_NOT_CHANGE_SYSTEM_ROLE("msg.007", "Can not change SYSTEM ROLE"),
	CAN_NOT_DELETE_SYSTEM_ROLE("msg.008", "Can not delete SYSTEM ROLE"),
	ROLE_NAME_ALREADY_EXIST("msg.009", "Tên ROLE đã tồn tại !"),
	
	
	/*File Exception*/
	
	TYPE_FILE_INVALID("msg.010", "Type file only are: image/video"),
	FILE_IS_NULL("msg.011", "File must be not null"),
	IS_NOT_AUTHOR_OR_ADMIN("msg.012", "Chỉ có tác giả/admin mới có thể xóa"),
	
	EMAIL_USERNAME_NOT_FOUND("msg.013", " not found"),
	
	
	ACCESS_DENIED("msg.403", "Không có quyền truy cập"),
	
	INTERNAL_SYSTEM_ERROR("msg.999", "Lỗi hệ thống, vui lòng kiểm tra và thử lại");
	
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
