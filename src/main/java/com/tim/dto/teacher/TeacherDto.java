package com.tim.dto.teacher;

import javax.validation.constraints.NotBlank;

import com.tim.dto.UserDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherDto extends UserDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 3200306173932990958L;

	private boolean isManager = false;
	
	@NotBlank
	private String facultyCode;
}
