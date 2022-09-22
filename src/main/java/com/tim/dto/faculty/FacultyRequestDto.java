package com.tim.dto.faculty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode
public class FacultyRequestDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -6110396477920437947L;

	@Size(max = 20, min = 3)
	@NotBlank
	@Code
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;
	
	@Code
	private String headOfFacultyUserId;
}
