package com.tim.dto.subject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.data.ValidationInput;
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

	@NotBlank(message = ValidationInput.General.CODE_NOTBLANK)
	@Size(max = 50, message = ValidationInput.General.CODE_MAX_SIZE)
	private String code;

	@NotBlank(message = ValidationInput.General.NAME_NOTBLANK)
	@Size(max = 50, message = ValidationInput.General.NAME_MAX_SIZE)
	private String name;

	@Min(value = 1)
	private int numberOfCredits = 1;

	private boolean mandatory = true;

	@Size(min = 0, max = 10, message = ValidationInput.Mark.MARKS_MAX_SIZE)
	private float passMarks;
}
