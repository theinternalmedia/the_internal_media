package com.tim.dto.notification;

import com.tim.data.TimConstants;
import com.tim.dto.NewsAndNotifyDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificationDto extends NewsAndNotifyDto {

	private static final long serialVersionUID = -6423343053212448460L;

	private int type = TimConstants.NotificationType.TO_ALL;
}
