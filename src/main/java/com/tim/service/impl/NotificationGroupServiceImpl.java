package com.tim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.NotificationGroupConverter;
import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupRequestDto;
import com.tim.dto.notification.NotificationGroupUpdateRequestDto;
import com.tim.entity.NotificationGroup;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.NotificationGroupRepository;
import com.tim.service.NotificationGroupService;
import com.tim.utils.ValidationUtils;

@Service
public class NotificationGroupServiceImpl implements NotificationGroupService {
	private static final String NOTIFICATION_GROUP = "Nhóm Thông Báo";

    @Autowired
    private NotificationGroupRepository notificationGroupRepository;
    @Autowired
    private NotificationGroupConverter notificationGroupConverter;

    @Override
    public NotificationGroupDto create(NotificationGroupRequestDto requestDto) {
    	// Validate input
    	ValidationUtils.validateObject(requestDto);
    	
        NotificationGroup entity = notificationGroupConverter.toEntity(requestDto);
        return notificationGroupConverter.toDto(
        		notificationGroupRepository.save(entity));
    }

    @Override
    public NotificationGroupDto update(NotificationGroupUpdateRequestDto requestDto) {
    	// Validate input
    	ValidationUtils.validateObject(requestDto);
    	NotificationGroup oldEntity = notificationGroupRepository.findById(requestDto.getId())
    			.orElseThrow(() -> new TimNotFoundException(
    					NOTIFICATION_GROUP, "ID", requestDto.getId().toString()));
    	oldEntity = notificationGroupConverter.toEntity(requestDto, oldEntity);
    	oldEntity = notificationGroupRepository.save(oldEntity);
        return notificationGroupConverter.toDto(oldEntity);
    }

    @Override
    public NotificationGroupDto getOne(String code) {
    	NotificationGroup entity = notificationGroupRepository.findByCode(code).orElseThrow(
    			() -> new TimNotFoundException(NOTIFICATION_GROUP, "Mã Nhóm Thông Báo", code));
        return notificationGroupConverter.toDto(entity);
    }

    @Override
    public Long toggleStatus(Long id) {
    	NotificationGroup entity = notificationGroupRepository.findById(id).orElseThrow(
    			() -> new TimNotFoundException(NOTIFICATION_GROUP, "ID", id.toString()));
    	entity.setStatus(!entity.getStatus());
    	notificationGroupRepository.save(entity);
        return id;
    }

	@Override
	public List<NotificationGroupDto> getAll(boolean status) {
		List<NotificationGroup> entities = notificationGroupRepository.findAllByStatus(status);
		List<NotificationGroupDto> dtos = notificationGroupConverter.toDtoList(entities);
		return dtos;
	}
}
