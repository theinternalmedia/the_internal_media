package com.tim.dto.notificationgroup;

import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NotificationGroupDto extends BaseDto{
	
	private static final long serialVersionUID = -5828572705001849961L;
	
	@Size(max = 50)
	private String code;

	@Size(max = 50)
	private String name;

}
