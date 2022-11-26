package com.tim.dto.student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.annotation.Password;
import com.tim.data.TimConstants.ApiModelPropertyValue;
import com.tim.dto.UserRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentCreateDto extends UserRequestDto {

	@NotBlank
	@Code
	private String classCode;
	
	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String userId;
	
	@NotBlank
	@Size(max = 20, min = 6)
	@Password
	private String password;
	
}
