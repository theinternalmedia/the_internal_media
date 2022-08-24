package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.dto.notification.NotificationUpdateRequestDto;

public interface NotificationService {

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @param thumbnail
	 * @return ResponseDto
	 */
	ResponseDto create(NotificationRequestDto requestDto, MultipartFile thumbnail);

	ResponseDto getOne(Long notificationId);

	PagingResponseDto getPage(int page, int size, String usersUserId, boolean isTeacher);

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @param thumbnail
	 * @return ResponseDto
	 */
	ResponseDto update(NotificationUpdateRequestDto requestDto, MultipartFile thumbnail);

	ResponseDto getOne(String slug);
}
