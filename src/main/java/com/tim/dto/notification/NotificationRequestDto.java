package com.tim.dto.notification;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.data.TimConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class NotificationRequestDto implements Serializable{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 7569445856618041347L;

	private int type = TimConstants.NotificationType.TO_ALL;

	@NotBlank
	@Size(max = 150)
	@ApiModelProperty(value = "Max length: 150", required = true)
	private String title;

	@NotBlank
	@Size(max = 255)
	@ApiModelProperty(value = "Max length: 255", required = true)
	private String shortDescription;

	@NotBlank
	private String content;

	@NotBlank
	@Code
	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE, required = true)
	private String notificationGroupCode;

	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private Set<String> facultyCodes = new HashSet<String>();

	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private Set<String> schoolYearCodes = new HashSet<String>();

	@ApiModelProperty(value = "Regex: " + TimConstants.REGEX_CODE)
	private Set<String> classCodes = new HashSet<String>();
}
