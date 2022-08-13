package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationStudentDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface NotificationStudentService {

    ResponseDto create(@Valid NotificationStudentDto dto);

    ResponseDto update(@Valid NotificationStudentDto dto);

    ResponseDto getOne(@NotBlank Long id);

    void toggleStatus(@NotBlank Long id);
}
