package com.tim.converter;

import com.tim.dto.notification.NotificationDto;
import com.tim.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter extends AbstractConverter<NotificationDto, Notification> {

    @Override
    public Notification toEntity(NotificationDto dto){
        return this.modelMapper.map(dto, Notification.class);
    }

    @Override
    public NotificationDto toDto(Notification entity){
        return this.modelMapper.map(entity, NotificationDto.class);
    }
}
