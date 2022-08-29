package com.tim.dto.schoolyear;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode
public class SchoolYearRequestDto {

	@Size(max = 20, min = 3)
	@NotBlank
	@Code
	private String code;

	@Size(max = 50)
	@NotBlank
	private String name;
}
