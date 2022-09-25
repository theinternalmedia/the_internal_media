package com.tim.dto;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public abstract class PageRequestDto {

	@Min(value = 1)
	private int page;

	@Min(value = 1)
	private int size;
	
	private boolean status = true;

	public boolean getStatus() {
		return status;
	}
}
