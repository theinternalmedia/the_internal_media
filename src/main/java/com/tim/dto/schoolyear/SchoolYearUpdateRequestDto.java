package com.tim.dto.schoolyear;

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
public class SchoolYearUpdateRequestDto extends SchoolYearRequestDto {

	@NotNull
	@Min(value = 1)
	private Long id;
}
