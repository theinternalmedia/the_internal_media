package com.tim.data;

public enum ETimRoles {
	ROLE_STUDENT("ROLE_STUDENT", "Sinh Viên"),
    ROLE_TEACHER("ROLE_TEACHER", "Giảng Viên"),
    ROLE_MANAGER("ROLE_MANAGER", "Ban Giám Hiệu"),
    ROLE_ADMIN("ROLE_ADMIN", "ADMIN - Quản Trị Viên");
    
    /**
	 * code of role
	 */
	public final String code;
	/**
	 * name
	 */
    public final String name;
    
    /**
     * ETimRoles
     * 
     * @param code
     * @param name
     */
    ETimRoles (String code, String name) {
        this.code = code;
        this.name = name;
    }
}
