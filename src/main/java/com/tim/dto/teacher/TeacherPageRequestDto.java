package com.tim.dto.teacher;

import com.tim.annotation.Code;
import com.tim.data.TimConstants;
import com.tim.dto.PageRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherPageRequestDto extends PageRequestDto{

	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String userId;
	
	private Boolean gender;

	@ApiModelProperty(value = "search in name, email, address, phone and remark")
	private String searchKey;
	
	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String facultyCode;
}
