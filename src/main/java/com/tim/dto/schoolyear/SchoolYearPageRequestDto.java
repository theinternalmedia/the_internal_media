package com.tim.dto.schoolyear;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolYearPageRequestDto extends PageRequestDto{

	@Code
	@Size(min =3, max = 50)
	@NotBlank
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;

}
