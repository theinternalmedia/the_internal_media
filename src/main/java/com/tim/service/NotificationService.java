package com.tim.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.dto.notification.NotificationPageRequestDto;
import com.tim.dto.notification.NotificationCreateDto;
import com.tim.dto.notification.NotificationUpdateDto;

public interface NotificationService {

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @param thumbnail
	 * @return ResponseDto
	 */
	NotificationDto create(NotificationCreateDto requestDto, MultipartFile thumbnail);

	NotificationDto getOne(Long notificationId);
	NotificationDto getOne(String slug);
	
	PagingResponseDto getPage(NotificationPageRequestDto pageRequestDto, boolean isAdmin);

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @param thumbnail
	 * @return ResponseDto
	 */
	NotificationDto update(NotificationUpdateDto requestDto, MultipartFile thumbnail);
	
	long toggleStatus(Set<Long> ids);
}
