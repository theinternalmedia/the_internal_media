package com.tim.dto.teacher;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.annotation.Password;
import com.tim.data.TimConstants.ApiModelPropertyValue;
import com.tim.dto.UserRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherCreateDto extends UserRequestDto {

	private boolean isManager = false;

	@NotBlank
	@Code
	private String facultyCode;
	
	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String userId;
	
	@NotBlank
	@Size(max = 20, min = 6)
	@Password
	private String password;
	
	private Set<String> roleCodes = new HashSet<>();
}
