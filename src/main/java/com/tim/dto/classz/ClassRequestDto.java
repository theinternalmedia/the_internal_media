package com.tim.dto.classz;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.Data;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
public class ClassRequestDto {

	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;
	
	@NotBlank
	private String facultyCode;
	
	private String advisorId;
	
	@NotBlank
	private String schoolYearCode;

}