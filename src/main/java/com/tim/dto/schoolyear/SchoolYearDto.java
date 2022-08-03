package com.tim.dto.schoolyear;

import com.tim.data.ValidationInput;
import com.tim.dto.BaseDto;

import javax.validation.constraints.Size;

/**
 * 
 * @appName the_internal_media
 *
 */
public class SchoolYearDto extends BaseDto {

	private static final long serialVersionUID = 2582106395890821136L;

	@Size(max = 50, message = ValidationInput.General.CODE_MAX_SIZE)
	private String code;

	@Size(max = 50, message = ValidationInput.General.NAME_MAX_SIZE)
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
