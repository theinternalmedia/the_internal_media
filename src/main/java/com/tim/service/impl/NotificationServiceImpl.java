package com.tim.service.impl;

import com.tim.converter.NotificationConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.repository.NotificationRepository;
import com.tim.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationConverter notificationConverter;

    @Override
    public ResponseDto create(NotificationDto dto) {
        return null;
    }

    @Override
    public ResponseDto update(NotificationDto dto) {
        return null;
    }

    @Override
    public ResponseDto getOne(Long id) {
        return null;
    }

    @Override
    public void toggleStatus(Long id) {

    }
}
