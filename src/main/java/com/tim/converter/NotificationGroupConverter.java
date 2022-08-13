package com.tim.converter;

import com.tim.dto.notification.NotificationGroupDto;
import com.tim.entity.NotificationGroup;
import org.springframework.stereotype.Component;

@Component
public class NotificationGroupConverter extends AbstractConverter<NotificationGroupDto, NotificationGroup> {

    @Override
    public NotificationGroup toEntity(NotificationGroupDto dto){
        return this.modelMapper.map(dto, NotificationGroup.class);
    }

    @Override
    public NotificationGroupDto toDto(NotificationGroup entity){
        return this.modelMapper.map(entity, NotificationGroupDto.class);
    }
}
