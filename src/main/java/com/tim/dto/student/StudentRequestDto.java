package com.tim.dto.student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class StudentRequestDto extends UserRequestDto {

	@NotBlank
	private String classCode;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
	
	@NotBlank
	@Size(max = 100, min = 6)
	private String password;
}
