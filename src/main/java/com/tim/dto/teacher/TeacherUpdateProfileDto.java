package com.tim.dto.teacher;

import com.tim.dto.UserUpdateProfileDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherUpdateProfileDto extends UserUpdateProfileDto{

	/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1599927856771499773L;

}
