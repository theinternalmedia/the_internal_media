package com.tim.dto.educationprogram;

import com.tim.annotation.Code;
import com.tim.data.TimConstants;
import com.tim.dto.PageRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationProgramPageRequestDto extends PageRequestDto {

	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String code;

	private String name;
	
	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String schoolYearCode;

	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String facultyCode;

}
