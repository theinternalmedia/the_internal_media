package com.tim.service.impl;

import com.tim.converter.NotificationTeacherConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationTeacherDto;
import com.tim.repository.NotificationTeacherRepository;
import com.tim.service.NotificationTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationTeacherServiceImpl implements NotificationTeacherService {

    @Autowired
    private NotificationTeacherRepository notificationTeacherRepository;
    @Autowired
    private NotificationTeacherConverter notificationTeacherConverter;

    @Override
    public ResponseDto create(NotificationTeacherDto dto) {
        return null;
    }

    @Override
    public ResponseDto update(NotificationTeacherDto dto) {
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
