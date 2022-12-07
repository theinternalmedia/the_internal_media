package com.tim.dto.subject;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectPageRequestDto extends PageRequestDto{

	@Code
	private String code;
	
	private String name;
	
}
