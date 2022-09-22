package com.tim.dto.notification;

import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Data
@EqualsAndHashCode(callSuper = true)
public class NotificationGroupDto extends BaseDto {

    @Getter(value = AccessLevel.NONE)
    private static final long serialVersionUID = 3376949652308421251L;


    @Size(max = 50)
    private String code;

    @Size(max = 100)
    private String name;
}
