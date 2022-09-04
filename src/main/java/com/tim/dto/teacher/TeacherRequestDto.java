package com.tim.dto.teacher;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.data.ETimRoles;
import com.tim.dto.UserRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherRequestDto extends UserRequestDto {

	private boolean isManager = false;

	@NotBlank
	@Code
	private String facultyCode;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
	
	@NotEmpty
	private Set<ETimRoles> roleCodes = new HashSet<>();
}
