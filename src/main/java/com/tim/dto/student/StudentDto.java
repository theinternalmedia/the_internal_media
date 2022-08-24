package com.tim.dto.student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.UserDto;

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
public class StudentDto extends UserDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6750034714277068245L;
	
	@Size(max = 100)
	private String remark;
	
	@NotBlank
	private String classCode;
}
