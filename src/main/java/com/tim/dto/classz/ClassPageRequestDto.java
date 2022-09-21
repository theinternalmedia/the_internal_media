package com.tim.dto.classz;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class ClassPageRequestDto {
	
	@Min(value = 1)
	private int page;
	
	@Min(value = 1)
	private int size;
	
	private String schoolYearCode;
	
	private String facultyCode;
	
	private String code;
	
	private String name;
	
	private Boolean status;
}
