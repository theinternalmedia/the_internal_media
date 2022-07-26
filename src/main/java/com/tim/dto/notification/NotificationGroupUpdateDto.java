package com.tim.dto.notification;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificationGroupUpdateDto extends NotificationGroupCreateDto{

	@NotNull
	@Min(value = 1)
	private Long id;

}
