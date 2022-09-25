package com.tim.dto.notification;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.tim.annotation.Code;
import com.tim.data.TimConstants;
import com.tim.dto.PageRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationPageRequestDto extends PageRequestDto{
	
	@ApiModelProperty(value = "search in title, content and shortDescription")
	private String searchKey;
	
	@Min(0)
	@Max(2)
	private Integer type;
	
	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private String notificationGroupCode;
}
