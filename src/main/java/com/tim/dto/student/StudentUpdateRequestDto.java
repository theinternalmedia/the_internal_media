package com.tim.dto.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.dto.UserRequestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @appName the_internal_media
 *
 */
@Getter
@Setter
@ToString
public class StudentUpdateRequestDto extends UserRequestDto {
	
	@NotNull
	@Min(value = 1)
	private Long id;

	@NotBlank
	private String classCode;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
	
	@Size(max = 100, min = 6)
	private String password;
}
