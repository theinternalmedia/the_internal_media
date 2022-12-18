package com.tim.data;

public enum ETimMessages {
	
	/* ========== EXCEPTION MESSAGE =================*/
	/** Thông tin tài khoản không chính xác. */
	USER_NOT_FOUND("msg.000", "Thông tin tài khoản không chính xác."),
	/** Không tìm thấy {0} với {1} = {2}. | Không tìm thấy đối tượng.*/
	ENTITY_NOT_FOUND("msg.001", "Không tìm thấy đối tượng."),
	/** Expired Token: {0}. | expired token.*/
	EXPIRED_TOKEN("msg.002", "expired token."),
	/** Invalid Token: {0}. | invalid token.*/
	INVALID_TOKEN("msg.003", "invalid token."),
	/** {0} đã tồn tại: {1}. | Đối tượng đã tồn tại.*/
	ALREADY_EXISTS("msg.004", "Đối tượng đã tồn tại."),
	/** Mật khẩu không chính xác*/
	PASSWORD_NOT_MATCH("msg.005", "Mật khẩu không đúng"),
	/** Truy cập thất bại(UnAuthorized). */
	UNAUTHORIZED("msg.006", "Truy cập thất bại(UnAuthorized)."), 
	/** Không tìm thấy file: {0}. | Không tìm thấy file.*/
	FILE_NOT_FOUND("msg.007", "Không tìm thấy file."),
	/** Truy cập thất bại(Forbidden). */
	FORBIDDEN("msg.008", "Truy cập thất bại(Forbidden)."), 
	/** Truy cập bị từ chối. */
	ACCESS_DENIED("msg.009", "Truy cập bị từ chối."),
	
	
	/* ========== VALIDATION MESSAGE =================*/
	/** Dữ liệu không hợp lệ. */
	VALIDATION_ERR_MESSAGE("msg.400", "Dữ liệu không hợp lệ."),
	/** Định dạng tệp tin phải là Excel. */
	INVALID_EXCEL_FILE("msg.401", "Định dạng tệp tin phải là Excel."),
	/** Danh sách {0} vừa nhập vào không đúng tại các ô. | Danh sách vừa nhập vào không đúng tại các ô.*/
	INVALID_EXCEL_VALUE("msg.402", "Danh sách vừa nhập vào không đúng tại các ô."),
	/** {0} vừa nhập vào không hợp lệ. | Dữ liệu vừa nhập vào không hợp lệ. */
	INVALID_OBJECT_VALUE("msg.403", "Dữ liệu nhập vào không hợp lệ"),
	/** {0}: Kiểu dữ liệu không đúng định dạng. | Kiểu dữ liệu không đúng định dạng.*/
	INVALID_CELL_VALUE("msg.404", "Kiểu dữ liệu không đúng định dạng."),
	/** {0}: Dữ liệu trống. | Dữ liệu trống.*/
	NULL_CELL_VALUE("msg.405", "Dữ liệu trống."),
	/** File hình ảnh không hợp lệ: {0}. | File hình ảnh không hợp lệ.*/
	INVALID_IMAGE_FILE("msg.406", "File hình ảnh không hợp lệ."),
	/** {0} vừa nhập vào không hợp lệ: {1}. | Dữ liệu vừa nhập vào không hợp lệ.*/
	INVALID_OBJECT_VALUE_2("msg.407", "Dữ liệu vừa nhập vào không hợp lệ."),
	/** {0} vừa nhập vào rỗng(null). | File vừa nhập vào rỗng(null).*/
	OBJECT_IS_NULL("msg.408", "File vừa nhập vào rỗng(null)."),

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
