package com.tim.dto.notification;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class NotificationGroupRequestDto {

	@Size(max = 50)
	private String code;

	@Size(max = 100)
	private String name;

}
