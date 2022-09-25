package com.tim.dto.notification;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.data.TimConstants;
import com.tim.data.TimConstants.ApiModelPropertyValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class NotificationCreateDto implements Serializable{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 7569445856618041347L;

	private int type = TimConstants.NotificationType.TO_ALL;

	@NotBlank
	@Size(max = 150)
	@ApiModelProperty(value = ApiModelPropertyValue.MAX_LENGTH_150, required = true)
	private String title;

	@NotBlank
	@Size(max = 255)
	@ApiModelProperty(value = ApiModelPropertyValue.MAX_LENGTH_255, required = true)
	private String shortDescription;

	@NotBlank
	private String content;

	@NotBlank
	@Code
	private String notificationGroupCode;

	private Set<String> facultyCodes = new HashSet<String>();

	private Set<String> schoolYearCodes = new HashSet<String>();

	private Set<String> classCodes = new HashSet<String>();
}
