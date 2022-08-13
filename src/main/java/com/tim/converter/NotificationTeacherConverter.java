package com.tim.converter;

import com.tim.dto.notification.NotificationTeacherDto;
import com.tim.entity.NotificationTeacher;
import org.springframework.stereotype.Component;

@Component
public class NotificationTeacherConverter extends AbstractConverter<NotificationTeacherDto, NotificationTeacher> {

    @Override
    public NotificationTeacher toEntity(NotificationTeacherDto dto){
        return this.modelMapper.map(dto, NotificationTeacher.class);
    }

    @Override
    public NotificationTeacherDto toDto(NotificationTeacher entity){
        return this.modelMapper.map(entity, NotificationTeacherDto.class);
    }
}
