package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupCreateDto;
import com.tim.dto.notification.NotificationGroupUpdateDto;
import com.tim.entity.NotificationGroup;

@Component
public class NotificationGroupConverter extends AbstractConverter<NotificationGroupDto, NotificationGroup> {

	@Override
	public NotificationGroup toEntity(NotificationGroupDto dto) {
		return this.modelMapper.map(dto, NotificationGroup.class);
	}

	@Override
	public NotificationGroupDto toDto(NotificationGroup entity) {
		return this.modelMapper.map(entity, NotificationGroupDto.class);
	}

	public NotificationGroup toEntity(NotificationGroupCreateDto requestDto) {
		return this.modelMapper.map(requestDto, NotificationGroup.class);
	}

	@Override
	public List<NotificationGroupDto> toDtoList(List<NotificationGroup> entityList) {
		List<NotificationGroupDto> result = new ArrayList<NotificationGroupDto>();
		entityList.forEach(item -> {
			result.add(toDto(item));
		});
		return result;
	}

	public NotificationGroup toEntity(
			NotificationGroupUpdateDto requestDto, NotificationGroup oldEntity) {
		oldEntity.setCode(requestDto.getCode());
		oldEntity.setName(requestDto.getName());
		return oldEntity;
	}
}
