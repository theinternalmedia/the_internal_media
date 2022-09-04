package com.tim.dto.educationprogram;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.BaseDto;

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
public class EducationProgramDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -2167333247842473644L;

	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	private String code;

	@Size(max = 50)
	@NotBlank
	private String name;
	
}
