package com.tim.dto.notification;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NotificationGroupRequestDto {

	@Size(max = 50)
	private String code;

	@Size(max = 50)
	private String name;

}
