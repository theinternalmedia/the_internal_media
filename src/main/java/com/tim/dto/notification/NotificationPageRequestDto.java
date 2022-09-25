package com.tim.dto.notification;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationPageRequestDto extends PageRequestDto{
	
	private String searchKey;
	
	@Min(0)
	@Max(2)
	private Integer type;
	
	@Code
	private String notificationGroupCode;
}
