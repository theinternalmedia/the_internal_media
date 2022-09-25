package com.tim.dto.educationprogram;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.data.TimConstants.ApiModelPropertyValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;


@Data
public class EducationProgramCreateDto implements Serializable{

	/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	
	@Size(max = 50, min = 3)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE + ", length: 3-50.")
	private String code;

	@Size(max = 100)
	@NotBlank
	@ApiModelProperty(value = ApiModelPropertyValue.MAX_LENGTH_100)
	private String name;
	
	@NotBlank
	private String schoolYearCode;
	
	@NotBlank
	private String facultyCode;
}
