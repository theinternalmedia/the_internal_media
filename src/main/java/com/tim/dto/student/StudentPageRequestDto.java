package com.tim.dto.student;

import javax.validation.constraints.Min;

import com.tim.annotation.Code;

import lombok.Data;

@Data
public class StudentPageRequestDto {

	@Min(value = 1)
	private int page;

	@Min(value = 1)
	private int size;

	@Code
	private String userId;

	private String name;
	
	@Code
	private String classCode;

	private boolean status = true;
	
	@Code
	private String facultyCode;
	
	@Code
	private String schoolYearCode;

	public boolean getStatus() {
		return status;
	}
}
