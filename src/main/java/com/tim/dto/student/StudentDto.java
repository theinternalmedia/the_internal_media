package com.tim.dto.student;

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
public class StudentDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6750034714277068245L;
	
	@Size(max = 100)
	private String remark;
	
	@NotBlank
	private String classCode;
}
