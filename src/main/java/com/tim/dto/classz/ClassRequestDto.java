package com.tim.dto.classz;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	private String code;

	@Size(max = 50)
	@NotBlank
	private String name;
	
	@NotBlank
	private String facultyCode;
	
	private String advisorId;
	
	@NotBlank
	private String schoolYearCode;

}