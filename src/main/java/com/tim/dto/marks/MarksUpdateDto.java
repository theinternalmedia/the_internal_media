package com.tim.dto.marks;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MarksUpdateDto extends MarksCreateDto{

	@NotNull
	@Min(value = 1)
	private Long id;
}
