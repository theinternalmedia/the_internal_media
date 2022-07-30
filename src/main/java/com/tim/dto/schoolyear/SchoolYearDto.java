package com.tim.dto.schoolyear;

import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

/**
 * 
 * @appName the_internal_media
 *
 */
public class SchoolYearDto extends BaseDto {

	private static final long serialVersionUID = 2582106395890821136L;

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