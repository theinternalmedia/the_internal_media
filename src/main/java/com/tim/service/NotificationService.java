package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface NotificationService {

    ResponseDto create(@Valid NotificationDto dto);

    ResponseDto update(@Valid NotificationDto dto);

    ResponseDto getOne(@NotBlank Long id);

    void toggleStatus(@NotBlank Long id);
}
