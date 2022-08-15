package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.service.NotificationService;
import com.tim.utils.ValidationUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class NotificationResource {
	
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping(TimApiPath.Notification.INSERT)
	public ResponseDto save(@RequestBody NotificationRequestDto requestDto) {
		ValidationUtils.validateObject(requestDto);
		return notificationService.insert(requestDto);
	}
	
	@GetMapping(TimApiPath.Notification.GET_ONE)
	public ResponseDto getOne(@PathVariable("notificationId") long notificationId) {
		return notificationService.getById(notificationId);
	}
	
	@GetMapping(TimApiPath.Notification.GET_PAGE)
	public PagingResponseDto getPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		return notificationService.getPage(page, size);
	}
}
