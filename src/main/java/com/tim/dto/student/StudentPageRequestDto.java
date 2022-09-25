package com.tim.dto.student;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPageRequestDto extends PageRequestDto{

	@Code
	private String userId;

	private String name;
	
	@Code
	private String classCode;
	
	@Code
	private String facultyCode;
	
	@Code
	private String schoolYearCode;

}
