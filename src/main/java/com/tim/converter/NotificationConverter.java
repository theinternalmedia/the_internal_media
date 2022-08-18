package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.notification.NotificationDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.entity.Notification;

@Component
public class NotificationConverter extends AbstractConverter<NotificationDto, Notification> {

	public Notification toEntity(NotificationRequestDto requestDto) {
		this.modelMapper.map(requestDto, Notification.class);
		return this.modelMapper.map(requestDto, Notification.class);
	}

	@Override
	public Notification toEntity(NotificationDto dto) {
		return this.modelMapper.map(dto, Notification.class);
	}

	@Override
	public NotificationDto toDto(Notification entity) {
		return modelMapper.map(entity, NotificationDto.class);
	}

	@Override
	public List<NotificationDto> toDtoList(List<Notification> entityList) {
		List<NotificationDto> result = new ArrayList<>();
		entityList.forEach(item -> {
			result.add(toDto(item));
		});
		return result;
	}
}
