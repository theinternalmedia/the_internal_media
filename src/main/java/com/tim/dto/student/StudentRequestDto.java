package com.tim.dto.student;

import javax.validation.constraints.NotBlank;
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
public class StudentRequestDto extends UserRequestDto {

	@NotBlank
	private String classCode;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
}
