package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationGroupRequestDto;
import com.tim.service.NotificationGroupService;
import com.tim.utils.ValidationUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class NotificationGroupResource {

	@Autowired
	private NotificationGroupService notificationGroupService;

	@PostMapping(TimApiPath.NotificationGroup.CREATE)
	public ResponseDto create(@RequestBody NotificationGroupRequestDto requestDto) {
		ValidationUtils.validateObject(requestDto);
		return notificationGroupService.create(requestDto);
	}

	@GetMapping(TimApiPath.NotificationGroup.GET_ONE)
	public ResponseDto getOne(@PathVariable("code") String code) {
		return notificationGroupService.getOne(code);
	}

	@GetMapping(TimApiPath.NotificationGroup.GET_ALL)
	public ResponseDto getAll() {
		return notificationGroupService.getAll();
	}
}
