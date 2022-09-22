package com.tim.dto.student;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class StudentPageRequestDto {

	@Min(value = 1)
	private int page;

	@Min(value = 1)
	private int size;

	private String userId;

	private String name;

	private String schoolYearCode;

	private String facultyCode;

	private String classCode;

	private boolean status = true;

	public boolean getStatus() {
		return status;
	}
}
