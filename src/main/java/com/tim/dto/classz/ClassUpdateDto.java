package com.tim.dto.classz;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClassUpdateDto extends ClassCreateDto{
	
	@NotNull
	@Min(value = 1)
	private Long id;
}
