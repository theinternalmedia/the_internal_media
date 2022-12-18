package com.tim.dto.marks;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MarksPageRequestDto extends PageRequestDto{

	@Code
	@NotBlank
	@Size(max = 20)
	private String subjectCode;
	
	@Code
	@NotBlank
	@Size(max = 20, min = 3)
	private String classCode;
}
