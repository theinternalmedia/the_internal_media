package com.tim.data;

public enum ETimPermissions {
	
	/* Class */
	READ_CLASS(Permissions.Classz.CREATE, "Thêm mới Lớp Học."),
	UPDATE_CLASS(Permissions.Classz.UPDATE, "Chỉnh sửa Lớp Học."),
	DELETE_CLASS(Permissions.Classz.READ, "Xem Lớp Học."),
	CREATE_CLASS(Permissions.Classz.TOGGLE_STATUS, "Cập nhật trạng thái Lớp Học(ẩn/hiện)."),
	
	/* EducationProgram */
	READ_EDUCATIONPROGRAM(Permissions.EducationProgram.CREATE, "Thêm mới Chương Trình Giảng Dạy."),
	UPDATE_EDUCATIONPROGRAM(Permissions.EducationProgram.UPDATE, "Chỉnh sửa Chương Trình Giảng Dạy."),
	DELETE_EDUCATIONPROGRAM(Permissions.EducationProgram.READ, "Xem Chương Trình Giảng Dạy."),
	CREATE_EDUCATIONPROGRAM(Permissions.EducationProgram.TOGGLE_STATUS, "Cập nhật trạng thái Chương Trình Giảng Dạy(ẩn/hiện)."),
	
	/* Faculty */
	READ_FACULTY(Permissions.Faculty.CREATE, "Thêm mới Khoa."),
	UPDATE_FACULTY(Permissions.Faculty.UPDATE, "Chỉnh sửa Khoa."),
	DELETE_FACULTY(Permissions.Faculty.READ, "Xem Khoa."),
	CREATE_FACULTY(Permissions.Faculty.TOGGLE_STATUS, "Cập nhật trạng Khoa(ẩn/hiện)."),
	
	/* Marks */
	READ_MARKS(Permissions.Marks.CREATE, "Thêm mới Điểm."),
	UPDATE_MARKS(Permissions.Marks.UPDATE, "Chỉnh sửa Điểm."),
	DELETE_MARKS(Permissions.Marks.READ, "Xem Điểm."),
	CREATE_MARKS(Permissions.Marks.TOGGLE_STATUS, "Cập nhật trạng Điểm(ẩn/hiện)."),
	
	/* News */
	READ_NEWS(Permissions.News.CREATE, "Thêm mới Tin Tức."),
	UPDATE_NEWS(Permissions.News.UPDATE, "Chỉnh sửa Tin Tức."),
	DELETE_NEWS(Permissions.News.READ, "Xem Tin Tức."),
	CREATE_NEWS(Permissions.News.TOGGLE_STATUS, "Cập nhật trạng Tin Tức(ẩn/hiện)."),
	
	/* Notification */
	READ_NOTIFICATION(Permissions.Notification.CREATE, "Thêm mới Thông Báo."),
	UPDATE_NOTIFICATION(Permissions.Notification.UPDATE, "Chỉnh sửa Thông Báo."),
	DELETE_NOTIFICATION(Permissions.Notification.READ, "Xem Thông Báo."),
	CREATE_NOTIFICATION(Permissions.Notification.TOGGLE_STATUS, "Cập nhật trạng Thông Báo(ẩn/hiện)."),
	
	/* NotificationGroup */
	READ_NOTIFICATION_GROUP(Permissions.NotificationGroup.CREATE, "Thêm mới Nhóm Thông Báo."),
	UPDATE_NOTIFICATION_GROUP(Permissions.NotificationGroup.UPDATE, "Chỉnh sửa Nhóm Thông Báo."),
	DELETE_NOTIFICATION_GROUP(Permissions.NotificationGroup.READ, "Xem Nhóm Thông Báo."),
	CREATE_NOTIFICATION_GROUP(Permissions.NotificationGroup.TOGGLE_STATUS, "Cập nhật trạng Nhóm Thông Báo(ẩn/hiện)."),
	
	/* Roles */
	READ_ROLES(Permissions.Roles.CREATE, "Thêm mới Vai Trò."),
	UPDATE_ROLES(Permissions.Roles.UPDATE, "Chỉnh sửa Vai Trò."),
	DELETE_ROLES(Permissions.Roles.READ, "Xem Vai Trò."),
	CREATE_ROLES(Permissions.Roles.TOGGLE_STATUS, "Cập nhật trạng Vai Trò(ẩn/hiện)."),
	
	/* Student */
	READ_STUDENT(Permissions.Student.CREATE, "Thêm mới Sinh Viên."),
	UPDATE_STUDENT(Permissions.Student.UPDATE, "Chỉnh sửa Sinh Viên."),
	DELETE_STUDENT(Permissions.Student.READ, "Xem Sinh Viên."),
	CREATE_STUDENT(Permissions.Student.TOGGLE_STATUS, "Cập nhật trạng Sinh Viên(ẩn/hiện)."),
	
	/* Subject */
	READ_SUBJECT(Permissions.Subject.CREATE, "Thêm mới Môn Học."),
	UPDATE_SUBJECT(Permissions.Subject.UPDATE, "Chỉnh sửa Môn Học."),
	DELETE_SUBJECT(Permissions.Subject.READ, "Xem Môn Học."),
	CREATE_SUBJECT(Permissions.Subject.TOGGLE_STATUS, "Cập nhật trạng Môn Học(ẩn/hiện)."),
	
	/* Teacher */
	READ_TEACHER(Permissions.Teacher.CREATE, "Thêm mới Giảng Viên."),
	UPDATE_TEACHER(Permissions.Teacher.UPDATE, "Chỉnh sửa Giảng Viên."),
	DELETE_TEACHER(Permissions.Teacher.READ, "Xem Giảng Viên."),
	CREATE_TEACHER(Permissions.Teacher.TOGGLE_STATUS, "Cập nhật trạng thái Giảng Viên(ẩn/hiện)."),
	
	/* Role */
	READ_ROLE(Permissions.Role.CREATE, "Thêm mới Role."),
	UPDATE_ROLE(Permissions.Role.UPDATE, "Chỉnh sửa Role."),
	DELETE_ROLE(Permissions.Role.READ, "Xem Role."),
	CREATE_ROLE(Permissions.Role.TOGGLE_STATUS, "Cập nhật trạng thái Role(ẩn/hiện)."),
	
	/* ACCESS_DASHBOARD */
	ACCESS_DASHBOARD("ACCESS_DASHBOARD", "Truy cập trang quản trị.");
	
	/**
	 * code of permission
	 */
	public final String code;
	/**
	 * name
	 */
    public final String name;
    
    /**
     * ETimPermissions
     * 
     * @param code
     * @param name
     */
    ETimPermissions (String code, String name) {
        this.code = code;
        this.name = name;
    }
}
