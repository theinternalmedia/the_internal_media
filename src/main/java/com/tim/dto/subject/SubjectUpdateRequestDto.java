package com.tim.dto.subject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectUpdateRequestDto extends SubjectRequestDto {

	@NotNull
	@Min(value = 1)
	private Long id;
}
