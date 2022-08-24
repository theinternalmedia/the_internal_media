package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupRequestDto;

public interface NotificationGroupService {

    ResponseDto create(NotificationGroupRequestDto dto);

    ResponseDto update(NotificationGroupDto dto);

    ResponseDto getOne(String code);

    void toggleStatus(Long id);

    ResponseDto getAll();
}
