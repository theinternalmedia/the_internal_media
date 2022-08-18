package com.tim.dto.notification;

import com.tim.dto.BaseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class NotificationGroupDto extends BaseDto {

    @Getter(value = AccessLevel.NONE)
    private static final long serialVersionUID = 3376949652308421251L;


    @Size(max = 50)
    private String code;

    @Size(max = 50)
    private String name;
}
