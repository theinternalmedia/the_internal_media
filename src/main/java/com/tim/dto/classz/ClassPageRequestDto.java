package com.tim.dto.classz;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassPageRequestDto {
	
	@Min(value = 1)
	private int page;
	
	@Min(value = 1)
	private int size;
	
	private String schoolYearCode;
	
	private String facultyCode;
	
	private String code;
	
	private String name;
	
	private boolean status;

	public boolean getStatus() {
		return status;
	}

}
