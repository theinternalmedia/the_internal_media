package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.dto.notification.NotificationUpdateRequestDto;

public interface NotificationService {

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @param thumbnail
	 * @return ResponseDto
	 */
	NotificationDto create(NotificationRequestDto requestDto, MultipartFile thumbnail);

	NotificationDto getOne(Long notificationId);
	NotificationDto getOne(String slug);
	
	PagingResponseDto getPage(int page, int size, String usersUserId, boolean isTeacher);

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @param thumbnail
	 * @return ResponseDto
	 */
	NotificationDto update(NotificationUpdateRequestDto requestDto, MultipartFile thumbnail);
}
