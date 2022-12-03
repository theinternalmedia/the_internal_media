package com.tim.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Phone;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRequestDto {
	
	@NotBlank
	@Size(max = 50, min = 4)
	private String name;

	@Email
	private String email;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private boolean gender = true;

	@Size(max = 100)
	private String address;

	@Phone
	private String phone;

	private LocalDate dob;
	
	@Size(max = 100)
	private String remark;
	
	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
