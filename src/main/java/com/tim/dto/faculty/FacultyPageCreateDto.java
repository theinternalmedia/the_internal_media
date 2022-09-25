package com.tim.dto.faculty;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyPageCreateDto extends PageRequestDto{

	@Code
	private String code;

	private String name;

}
