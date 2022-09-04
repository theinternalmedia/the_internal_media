package com.tim.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.data.ETimRoles;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateRequestDto extends UserRequestDto{
	
	@NotNull
	@Min(value = 1)
	private Long id;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
	
	private boolean isHeadOfFaculty = false;
	
	private boolean isManager = false;
	
	private Boolean status = true;
	
	private Set<ETimRoles> roleCodes = new HashSet<>();
	
	private Set<String> classCodes = new HashSet<String>();
	
	private String facultyCode;
	private String classCode;
}
