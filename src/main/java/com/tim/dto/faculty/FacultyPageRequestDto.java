package com.tim.dto.faculty;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class FacultyPageRequestDto {

	@Min(value = 1)
	private int page;

	@Min(value = 1)
	private int size;

	private String code;

	private String name;

	private boolean status = true;

	public boolean getStatus() {
		return status;
	}
}
