package com.tim.dto.educationprogram;

import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

/**
 * 
 * @appName the_internal_media
 *
 */
public class EducationProgramDto extends BaseDto {

	private static final long serialVersionUID = -2167333247842473644L;

	@Size(max = 50)
	private String code;

	@Size(max = 50)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}