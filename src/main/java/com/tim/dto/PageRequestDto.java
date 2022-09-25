package com.tim.dto;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public abstract class PageRequestDto {

	@Min(value = 1)
	@ApiModelProperty(required = true, example = "1")
	private int page;

	@Min(value = 1)
	@ApiModelProperty(required = true, example = "10")
	private int size;
	
	@ApiModelProperty(value = "default true")
	private boolean status = true;

	public boolean getStatus() {
		return status;
	}
}
