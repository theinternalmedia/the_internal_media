package com.tim.dto.educationprogram;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;


@Data
public class EducationProgramRequestDto implements Serializable{

	/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	
	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;
	
	@NotBlank
	private String schoolYearCode;
	
	@NotBlank
	private String facultyCode;
}
