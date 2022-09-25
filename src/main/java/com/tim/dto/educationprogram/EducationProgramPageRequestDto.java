package com.tim.dto.educationprogram;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationProgramPageRequestDto extends PageRequestDto {

	@Code
	private String code;

	private String name;
	
	@Code
	private String schoolYearCode;

	@Code
	private String facultyCode;

}
