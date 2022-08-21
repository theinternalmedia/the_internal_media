package com.tim.dto.notification;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.data.TimConstants;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NotificationRequestDto {

	private int type = TimConstants.NotificationType.TO_ALL;

	@NotBlank
	@Size(max = 150)
	private String title;

	@NotBlank
	@Size(max = 255)
	private String shortDescription;

	@NotBlank
	private String content;

	@NotBlank
	private String notificationGroupCode;

	private Set<String> facultyCodes = new HashSet<String>();

	private Set<String> schoolYearCodes = new HashSet<String>();

	private Set<String> classCodes = new HashSet<String>();
}
