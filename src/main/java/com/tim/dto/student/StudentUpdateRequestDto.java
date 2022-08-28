package com.tim.dto.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.UserRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentUpdateRequestDto extends UserRequestDto {
	
	@NotNull
	@Min(value = 1)
	private Long id;

	@NotBlank
	@Code
	private String classCode;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
	
	@Size(max = 100, min = 6)
	private String password;
}
