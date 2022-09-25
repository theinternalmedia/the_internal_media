package com.tim.dto.educationprogram;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Data
@EqualsAndHashCode(callSuper = true)
public class EducationProgramUpdateDto extends EducationProgramRequestDto{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	@NotNull
	@Min(value = 1)
	@ApiModelProperty(value = "Min: 1", required = true, example = "1")
	private Long id;
	
}
