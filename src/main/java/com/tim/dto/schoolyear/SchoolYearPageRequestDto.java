package com.tim.dto.schoolyear;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class SchoolYearPageRequestDto {

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
