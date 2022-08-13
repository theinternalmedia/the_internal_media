package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationRequestDto;

public interface NotificationService {

	ResponseDto save(NotificationRequestDto requestDto);
}
