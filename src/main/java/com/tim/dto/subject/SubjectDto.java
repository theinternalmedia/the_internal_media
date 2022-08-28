package com.tim.dto.subject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.BaseDto;

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
@EqualsAndHashCode(callSuper = true)
public class SubjectDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1295707391155869293L;

	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	private String code;

	@Size(max = 50)
	@NotBlank
	private String name;

	@Min(value = 1)
	private int numberOfCredits = 1;

	private boolean mandatory = true;

	@Size(min = 0, max = 10)
	private float passMarks;
}
