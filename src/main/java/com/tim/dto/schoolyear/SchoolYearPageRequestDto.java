package com.tim.dto.schoolyear;

import com.tim.data.TimConstants;
import com.tim.dto.PageRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolYearPageRequestDto extends PageRequestDto{

	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String code;

	private String name;

}
