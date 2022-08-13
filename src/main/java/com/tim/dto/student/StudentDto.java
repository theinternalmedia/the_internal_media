package com.tim.dto.student;

import com.tim.dto.UserDto;
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
public class StudentDto extends UserDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6750034714277068245L;


}
