package com.tim.dto.notification;

import com.tim.data.TimConstants;
import com.tim.dto.BaseDto;
import com.tim.dto.news.NewsDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
public class NotificationDto extends BaseDto {

    @Getter(value = AccessLevel.NONE)
    private static final long serialVersionUID = 5386488123098261638L;

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String shortDescription;

    @NotBlank
    private String content;

    private String thumbnail;

    private int type = TimConstants.NotificationType.TO_ALL;
}
