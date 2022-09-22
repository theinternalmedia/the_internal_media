package com.tim.dto.educationprogram;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class EducationProgramPageRequestDto {

	@Min(value = 1)
	private int page;

	@Min(value = 1)
	private int size;

	private String code;

	private String name;
	
	private String schoolYearCode;

	private String facultyCode;

	private boolean status = true;

	public boolean getStatus() {
		return status;
	}
}
