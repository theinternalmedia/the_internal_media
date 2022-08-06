package com.tim.dto.subject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

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
public class SubjectDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1295707391155869293L;

	@NotBlank
	@Size(max = 20)
	private String code;

	@NotBlank
	@Size(max = 50)
	private String name;

	@Min(value = 1)
	private int numberOfCredits = 1;

	private boolean mandatory = true;

	@Size(min = 0, max = 10)
	private float passMarks;
}
