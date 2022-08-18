package com.tim.service.impl;

import com.tim.converter.NotificationGroupConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationGroupDto;
import com.tim.entity.NotificationGroup;
import com.tim.repository.NotificationGroupRepository;
import com.tim.service.NotificationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationGroupServiceImpl implements NotificationGroupService {

    @Autowired
    private NotificationGroupRepository notificationGroupRepository;
    @Autowired
    private NotificationGroupConverter notificationGroupConverter;

    @Override
    public ResponseDto create(NotificationGroupDto dto) {
        NotificationGroup entity = notificationGroupConverter.toEntity(dto);
        return new ResponseDto(notificationGroupConverter.toDto(
        		notificationGroupRepository.save(entity)));
    }

    @Override
    public ResponseDto update(NotificationGroupDto dto) {
        return null;
    }

    @Override
    public ResponseDto getOne(String code) {
        return null;
    }

    @Override
    public void toggleStatus(Long id) {

    }
}
