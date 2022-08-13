package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationTeacherDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface NotificationTeacherService {

    ResponseDto create(@Valid NotificationTeacherDto dto);

    ResponseDto update(@Valid NotificationTeacherDto dto);

    ResponseDto getOne(@NotBlank Long id);

    void toggleStatus(@NotBlank Long id);
}
