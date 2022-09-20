package com.tim.dto.classz;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClassPageRequestDto {
	
	@NotBlank
	@Min(value = 1)
	private int page;
	
	@NotBlank
	@Min(value = 1)
	private int size;
	
	private String schoolYearCode;
	
	private String facultyCode;
	
	private String code;
	
	private String name;
	
	@NotBlank
	private Boolean status;
}
