package com.tim.dto.student;

import com.tim.dto.UserUpdateUserDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentUpdateStudentDto extends UserUpdateUserDto{

	/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -7450482504577831880L;

	
}
