package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.service.NotificationService;
import com.tim.utils.ValidationUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.Notification.PREFIX)
public class NotificationResource {
	
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
	public ResponseDto save(@RequestBody NotificationRequestDto requestDto) {
		ValidationUtils.validateObject(requestDto);
		return notificationService.save(requestDto);
	}
}
