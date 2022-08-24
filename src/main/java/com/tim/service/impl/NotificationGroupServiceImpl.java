package com.tim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.NotificationGroupConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupRequestDto;
import com.tim.entity.NotificationGroup;
import com.tim.repository.NotificationGroupRepository;
import com.tim.service.NotificationGroupService;

@Service
public class NotificationGroupServiceImpl implements NotificationGroupService {

    @Autowired
    private NotificationGroupRepository notificationGroupRepository;
    @Autowired
    private NotificationGroupConverter notificationGroupConverter;

    @Override
    public ResponseDto create(NotificationGroupRequestDto requestDto) {
        NotificationGroup entity = notificationGroupConverter.toEntity(requestDto);
        return new ResponseDto(notificationGroupConverter.toDto(
        		notificationGroupRepository.save(entity)));
    }

    @Override
    public ResponseDto update(NotificationGroupDto dto) {
        return null;
    }

    @Override
    public ResponseDto getOne(String code) {
    	NotificationGroup entity = notificationGroupRepository.findByCode(code);
    	NotificationGroupDto dto = notificationGroupConverter.toDto(entity);
        return new ResponseDto(dto);
    }

    @Override
    public void toggleStatus(Long id) {
    	
    }

	@Override
	public ResponseDto getAll() {
		List<NotificationGroup> entities = notificationGroupRepository.findAllByStatusTrue();
		List<NotificationGroupDto> dtos = notificationGroupConverter.toDtoList(entities);
		return new ResponseDto(dtos);
	}
}
