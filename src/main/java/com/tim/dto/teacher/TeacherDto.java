package com.tim.dto.teacher;

import com.tim.dto.UserDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeacherDto extends UserDto {

	private static final long serialVersionUID = 3200306173932990958L;

	private boolean isManager = false;
}
