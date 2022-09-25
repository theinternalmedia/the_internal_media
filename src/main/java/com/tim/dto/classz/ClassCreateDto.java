package com.tim.dto.classz;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.data.TimConstants.ApiModelPropertyValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
public class ClassCreateDto {

	@Size(max = 20, min = 3)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;
	
	@Size(max = 50, min = 3)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String facultyCode;
	
	@Size(max = 20, min = 3)
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String advisorId;
	
	@Size(max = 50, min = 3)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String schoolYearCode;

}