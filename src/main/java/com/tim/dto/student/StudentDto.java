package com.tim.dto.student;

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
public class StudentDto extends BaseDto {

	private static final long serialVersionUID = 6750034714277068245L;
	
	private String remark;
}
