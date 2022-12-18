package com.tim.dto.student;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.tim.annotation.Code;
import com.tim.dto.UserDto;
import com.tim.dto.role.RoleDto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */

@EqualsAndHashCode(callSuper = true)
public class StudentDto extends UserDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6750034714277068245L;
	
	@NotBlank
	@Code
	private String classCode;
	
	private Set<RoleDto> roles = new HashSet<>();

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	
}
