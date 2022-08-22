package com.tim.dto.student;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Phone;

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
public class StudentRequestDto {

	@NotBlank
	@Size(max = 20, min = 5)
	private String userId;

	@NotBlank
	@Size(max = 50, min = 4)
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(max = 100, min = 6)
	private String password;

	private boolean gender = true;

	@Size(max = 100)
	private String address;

	@Phone
	private String phone;

	private LocalDate dob;

	@NotBlank
	private String classCode;

	@Size(max = 100)
	private String remark;

	private String avatar;
}
