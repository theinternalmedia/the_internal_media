package com.tim.dto.student;

import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPageRequestDto extends PageRequestDto{

	@Code
	@Size(min = 5, max = 20)
	private String userId;
	
	private Boolean gender;

	@ApiModelProperty(value = "search in name, email, address, phone and remark")
	private String searchKey;
	
	@Code
	private String classCode;
	
	@Code
	private String facultyCode;
	
	@Code
	private String schoolYearCode;

}
