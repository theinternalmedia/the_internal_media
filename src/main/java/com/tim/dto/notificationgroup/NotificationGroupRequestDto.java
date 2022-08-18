package com.tim.dto.notificationgroup;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NotificationGroupRequestDto{
	
	@Size(max = 50)
	private String code;

	@Size(max = 50)
	private String name;

}
