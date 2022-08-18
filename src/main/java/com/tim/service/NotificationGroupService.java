package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationGroupDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface NotificationGroupService {

    ResponseDto create(@Valid NotificationGroupDto dto);

    ResponseDto update(@Valid NotificationGroupDto dto);

    ResponseDto getOne(@NotBlank String code);

    void toggleStatus(@NotBlank Long id);
}
