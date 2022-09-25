package com.tim.service;

import java.util.List;
import java.util.Set;

import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupCreateDto;
import com.tim.dto.notification.NotificationGroupUpdateDto;

public interface NotificationGroupService {

	NotificationGroupDto create(NotificationGroupCreateDto dto);

	NotificationGroupDto update(NotificationGroupUpdateDto dto);

	NotificationGroupDto getOne(String code);

	long toggleStatus(Set<Long> ids);

    List<NotificationGroupDto> getAll(boolean status);
}
