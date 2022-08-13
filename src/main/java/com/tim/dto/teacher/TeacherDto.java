package com.tim.dto.teacher;

import javax.validation.constraints.NotBlank;

import com.tim.dto.UserDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeacherDto extends UserDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 3200306173932990958L;

	private boolean isManager = false;
	
	@NotBlank
	private String facultyCode;
}
