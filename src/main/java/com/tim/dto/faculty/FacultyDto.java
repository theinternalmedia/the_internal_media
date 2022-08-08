package com.tim.dto.faculty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @appName the_internal_media
 *
 */
@Getter
@Setter
@ToString
public class FacultyDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -6110396477920437947L;

	@Size(max = 20, min = 5)
	@NotBlank
	private String code;

	@Size(max = 50)
	@NotBlank
	private String name;
}
