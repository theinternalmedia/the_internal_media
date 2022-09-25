package com.tim.dto.student;

import com.tim.annotation.Code;
import com.tim.data.TimConstants;
import com.tim.dto.PageRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPageRequestDto extends PageRequestDto{

	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String userId;
	
	private Boolean gender;

	@ApiModelProperty(value = "search in name, email, address, phone and remark")
	private String searchKey;
	
	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String classCode;
	
	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String facultyCode;
	
	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String schoolYearCode;

}
