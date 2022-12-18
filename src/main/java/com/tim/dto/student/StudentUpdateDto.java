package com.tim.dto.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.annotation.Password;
import com.tim.data.TimConstants.ApiModelPropertyValue;
import com.tim.dto.UserRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class StudentUpdateDto extends UserRequestDto {
	
	@NotNull
	@Min(value = 1)
	private Long id;
	
	@Size(max = 20, min = 6)
	@Password
	private String password;
	
	@NotBlank
	@Code
	private String classCode;
	
	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String userId;
}
