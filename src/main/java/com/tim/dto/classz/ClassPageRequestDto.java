package com.tim.dto.classz;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassPageRequestDto extends PageRequestDto{
	
	@Code
	private String schoolYearCode;
	
	@Code
	private String facultyCode;
	
	@Code
	private String code;
	
	private String name;

}
