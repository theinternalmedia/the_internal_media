package com.tim.converter;

import com.tim.dto.notification.NotificationStudentDto;
import com.tim.entity.NotificationStudent;
import org.springframework.stereotype.Component;

@Component
public class NotificationStudentConverter extends AbstractConverter<NotificationStudentDto, NotificationStudent> {

    @Override
    public NotificationStudent toEntity(NotificationStudentDto dto){
        return this.modelMapper.map(dto, NotificationStudent.class);
    }

    @Override
    public NotificationStudentDto toDto(NotificationStudent entity){
        return this.modelMapper.map(entity, NotificationStudentDto.class);
    }
}
