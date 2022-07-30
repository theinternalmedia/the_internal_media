package com.tim.dto.role;

import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

/**
 * 
 * @appName the_internal_media
 *
 */
public class RoleDto extends BaseDto {

	private static final long serialVersionUID = -2024352697260945512L;

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

	public RoleDto() {
	}

	public RoleDto(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}