package com.tim.dto.teacher;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.UserRequestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeacherRequestDto extends UserRequestDto {

	private boolean isManager = false;

	@NotBlank
	private String facultyCode;
	
	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;
}
