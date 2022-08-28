package com.tim.service;

import java.util.List;

import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupRequestDto;
import com.tim.dto.notification.NotificationGroupUpdateRequestDto;

public interface NotificationGroupService {

	NotificationGroupDto create(NotificationGroupRequestDto dto);

	NotificationGroupDto update(NotificationGroupUpdateRequestDto dto);

	NotificationGroupDto getOne(String code);

    Long toggleStatus(Long id);

    List<NotificationGroupDto> getAll(boolean status);
}
