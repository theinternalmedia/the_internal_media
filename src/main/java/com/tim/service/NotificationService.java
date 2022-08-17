package com.tim.service;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationRequestDto;

public interface NotificationService {

	ResponseDto insert(NotificationRequestDto requestDto);

	ResponseDto getById(Long notificationId);

	PagingResponseDto getPage(int page, int size, String usersUserId, boolean isTeacher);
}
