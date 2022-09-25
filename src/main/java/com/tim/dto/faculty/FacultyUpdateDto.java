package com.tim.dto.faculty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FacultyUpdateDto extends FacultyCreateDto{

	@NotNull
	@Min(value = 1)
	private Long id;

}
