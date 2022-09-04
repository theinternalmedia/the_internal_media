package com.tim.service;

import java.util.List;
import java.util.Set;

import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupRequestDto;
import com.tim.dto.notification.NotificationGroupUpdateRequestDto;

public interface NotificationGroupService {

	NotificationGroupDto create(NotificationGroupRequestDto dto);

	NotificationGroupDto update(NotificationGroupUpdateRequestDto dto);

	NotificationGroupDto getOne(String code);

	long toggleStatus(Set<Long> ids);

    List<NotificationGroupDto> getAll(boolean status);
}
