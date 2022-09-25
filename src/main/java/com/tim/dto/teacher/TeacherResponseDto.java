package com.tim.dto.teacher;

import javax.validation.constraints.NotBlank;

import com.tim.annotation.Code;
import com.tim.dto.UserDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherResponseDto extends UserDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 3200306173932990958L;

	private boolean isManager = false;
	
	@NotBlank
	@Code
	private String facultyCode;
	
	private String facultyName;
}
