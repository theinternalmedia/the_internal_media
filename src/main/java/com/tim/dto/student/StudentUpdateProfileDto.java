package com.tim.dto.student;

import com.tim.dto.UserUpdateProfileDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentUpdateProfileDto extends UserUpdateProfileDto{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -7450482504577831880L;

	
}
