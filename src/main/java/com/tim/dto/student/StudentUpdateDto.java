package com.tim.dto.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentUpdateDto extends StudentCreateDto {
	
	@NotNull
	@Min(value = 1)
	private Long id;

}
