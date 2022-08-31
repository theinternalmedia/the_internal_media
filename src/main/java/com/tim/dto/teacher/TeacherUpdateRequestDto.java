package com.tim.dto.teacher;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherUpdateRequestDto extends TeacherRequestDto {
	
	@NotNull
	@Min(value = 1)
	private Long id;

}
