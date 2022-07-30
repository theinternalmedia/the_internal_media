package com.tim.dto.teacher;

import com.tim.dto.UserDto;

public class TeacherDto extends UserDto {

	/**
	 * thinhnguyen
	 */

	private static final long serialVersionUID = 3200306173932990958L;

	private boolean isHeadOfFaculty = false;

	private boolean isManager = false;

	public boolean isHeadOfFaculty() {
		return isHeadOfFaculty;
	}

	public void setHeadOfFaculty(boolean headOfFaculty) {
		isHeadOfFaculty = headOfFaculty;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean manager) {
		isManager = manager;
	}
}
