package com.tim.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.annotation.Phone;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -551690367785937349L;


	@NotBlank
	@Size(max = 20, min = 5)
	@Code
	private String userId;

	@NotBlank
	@Size(max = 50, min = 4)
	private String name;

	@Email
	private String email;

	@NotBlank
	@Size(max = 100, min = 6)
	private String password;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private boolean gender = true;

	@Size(max = 100)
	private String address;

	@Phone
	private String phone;

	private LocalDate dob;

	private String avatar;

	private String remark;

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	

}
