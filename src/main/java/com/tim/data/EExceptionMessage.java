package com.tim.data;

public enum EExceptionMessage {
	/*User exception message*/
	USER_NOT_FOUND(111, "Tài khoản không tồn tại"),
	USER_NAME_ALREADY_EXISTS(111, "Tài khoản đã tồn tại"),
	EMAIL_ALREADY_EXISTS(111, "Email đã tồn tại"),
	PASSWORD_NOT_MATCH(111, "Mật khẩu không đúng"),
	PASSWORD_NOT_INVALID(111, "Mật khẩu không hợp lệ"),
	CAN_NOT_CHANGE_ADMIN_ROOT(111, "Can not change ADMIN ROOT"),
	
	/*Role exception message*/
	CAN_NOT_CHANGE_SYSTEM_ROLE(333, "Can not change SYSTEM ROLE"),
	CAN_NOT_DELETE_SYSTEM_ROLE(333, "Can not delete SYSTEM ROLE"),
	ROLE_NAME_ALREADY_EXIST(333, "Tên ROLE đã tồn tại !"),
	
	
	/*File Exception*/
	
	TYPE_FILE_INVALID(444, "Type file only are: image/video"),
	FILE_IS_NULL(444, "File must be not null"),
	IS_NOT_AUTHOR_OR_ADMIN(444, "Chỉ có tác giả/admin mới có thể xóa"),
	
	REIGSTER_COURSE_EXISTS(555, "Khóa học đã tồn tại trong hồ sơ"),
	
	SEND_MAIL_ERROR(666, "Send Mail error"),
	
	EMAIL_USERNAME_NOT_FOUND(777, " not found"),
	
	
	ACCESS_DENIED(403, "Không có quyền truy cập"),
	
	INTERNAL_SYSTEM_ERROR(999, "Lỗi hệ thống, vui lòng kiểm tra và thử lại");
	
	public final Integer code;
    public final String message;
    EExceptionMessage (Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
