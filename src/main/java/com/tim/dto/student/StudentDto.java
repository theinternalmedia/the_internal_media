package com.tim.dto.student;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.data.ValidationInput;
import com.tim.dto.BaseDto;
import com.tim.dto.role.RoleDto;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 6750034714277068245L;
	
	@NotBlank(message = ValidationInput.Teacher.ID_NOTBLANK)
	@Size(max = 20, message = ValidationInput.Teacher.ID_MAXSIZE)
	private String userId;

	@NotBlank
	@Size(max = 50)
	private String name;

	@NotBlank(message = "{email.notblank}")
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;

	private boolean gender = true;

	@Size(max = 100)
	private String address;

	@Size(max = 10)
	private String phone;

	private LocalDate dob;

	@Size(max = 100)
	private String avatar;

	private Set<RoleDto> roles = new HashSet<RoleDto>();

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
