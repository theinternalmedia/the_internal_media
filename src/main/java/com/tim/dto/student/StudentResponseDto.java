package com.tim.dto.student;

import javax.validation.constraints.NotBlank;

import com.tim.annotation.Code;
import com.tim.dto.UserDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentResponseDto extends UserDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6750034714277068245L;
	
	@NotBlank
	@Code
	private String classCode;
	
	private String className;
}
