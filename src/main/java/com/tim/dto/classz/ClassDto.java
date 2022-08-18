package com.tim.dto.classz;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

import lombok.AccessLevel;
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
public class ClassDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -8648320837995659089L;

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