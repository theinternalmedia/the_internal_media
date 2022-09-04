package com.tim.dto.educationprogram;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

	@Size(max = 50)
	@NotBlank
	private String name;
	
	/*
	 * @NotBlank private String semester;
	 * 
	 * private String note;
	 */
	
	/*
	 * @NotEmpty private List<String> subjectCodes = new ArrayList<>();
	 */
	
	@NotNull
	private String schoolYearCode;
	
	@NotNull
	private String facultyCode;
}
