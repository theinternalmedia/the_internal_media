package com.tim.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.annotation.Phone;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserUpdateUserDto implements Serializable{/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -2167221737321020296L;

	@NotNull
	@Min(value = 1)
	private Long id;
	
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

	public boolean setGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	
}
