package com.tim.dto.teacher;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.data.ETimRoles;
import com.tim.dto.UserRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherUpdateRequestDto extends UserRequestDto {
	
	@NotNull
	@Min(value = 1)
	private Long id;

	private boolean isManager = false;

	@NotBlank
	private String facultyCode;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
	
	@Size(max = 100, min = 6)
	private String password;
	
	@NotEmpty
	private Set<ETimRoles> roleCodes = new HashSet<>();
}
