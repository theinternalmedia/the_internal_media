package com.tim.dto.schoolyear;

import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolYearPageRequestDto extends PageRequestDto{

	private String code;

	private String name;

}
