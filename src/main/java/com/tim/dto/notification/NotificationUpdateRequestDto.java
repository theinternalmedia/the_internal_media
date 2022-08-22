package com.tim.dto.notification;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NotificationUpdateRequestDto extends NotificationRequestDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6782895690948466224L;
	
	@NotNull
	@Min(value = 1)
	private Long id;
}
