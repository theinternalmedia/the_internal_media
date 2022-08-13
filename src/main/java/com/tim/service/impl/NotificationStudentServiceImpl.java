package com.tim.service.impl;

import com.tim.converter.NotificationStudentConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationStudentDto;
import com.tim.repository.NotificationStudentRepository;
import com.tim.service.NotificationStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationStudentServiceImpl implements NotificationStudentService {

    @Autowired
    private NotificationStudentRepository notificationStudentRepository;
    @Autowired
    private NotificationStudentConverter notificationStudentConverter;

    @Override
    public ResponseDto create(NotificationStudentDto dto) {
        return null;
    }

    @Override
    public ResponseDto update(NotificationStudentDto dto) {
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
