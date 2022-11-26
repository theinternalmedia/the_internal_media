package com.tim.dto.teacher;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.annotation.Password;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherUpdateDto extends TeacherCreateDto {
	
	@NotNull
	@Min(value = 1)
	private Long id;
	
	@Size(max = 20, min = 6)
	@Password
	private String password;

}
