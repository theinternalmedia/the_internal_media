package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.NotificationGroupConverter;
import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupCreateDto;
import com.tim.dto.notification.NotificationGroupUpdateDto;
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
    public NotificationGroupDto create(NotificationGroupCreateDto requestDto) {
    	// Validate input
    	ValidationUtils.validateObject(requestDto);
    	
        NotificationGroup entity = notificationGroupConverter.toEntity(requestDto);
        return notificationGroupConverter.toDto(
        		notificationGroupRepository.save(entity));
    }

    @Override
    public NotificationGroupDto update(NotificationGroupUpdateDto requestDto) {
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
    	NotificationGroup entity = notificationGroupRepository.findByCodeAndStatusTrue(code)
    			.orElseThrow(() -> new TimNotFoundException(
    					NOTIFICATION_GROUP, "Mã Nhóm Thông Báo", code));
        return notificationGroupConverter.toDto(entity);
    }

    @Override
    public long toggleStatus(Set<Long> ids) {
    	List<NotificationGroup> notificationGroups = new ArrayList<NotificationGroup>();
    	NotificationGroup entity;
    	for (Long id : ids) {
    		entity = notificationGroupRepository.findById(id).orElseThrow(
        			() -> new TimNotFoundException(NOTIFICATION_GROUP, "ID", id.toString()));
    		entity.setStatus(!entity.getStatus());
        	notificationGroups.add(entity);
    	}
    	notificationGroupRepository.saveAll(notificationGroups);
        return ids.size();
    }

	@Override
	public List<NotificationGroupDto> getAll(boolean status) {
		List<NotificationGroup> entities = notificationGroupRepository.findAllByStatus(status);
		List<NotificationGroupDto> dtos = notificationGroupConverter.toDtoList(entities);
		return dtos;
	}
}
