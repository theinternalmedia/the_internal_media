package com.tim.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.dto.notification.NotificationPageRequestDto;
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
	
	PagingResponseDto getPage(NotificationPageRequestDto pageRequestDto, boolean isAdmin);

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @param thumbnail
	 * @return ResponseDto
	 */
	NotificationDto update(NotificationUpdateRequestDto requestDto, MultipartFile thumbnail);
	
	long toggleStatus(Set<Long> ids);
}
