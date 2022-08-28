package com.tim.restful;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.notification.NotificationGroupDto;
import com.tim.dto.notification.NotificationGroupRequestDto;
import com.tim.dto.notification.NotificationGroupUpdateRequestDto;
import com.tim.service.NotificationGroupService;
import com.tim.utils.ValidationUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class NotificationGroupResource {

	@Autowired
	private NotificationGroupService notificationGroupService;

	@PostMapping(TimApiPath.NotificationGroup.CREATE)
	public NotificationGroupDto create(@RequestBody NotificationGroupRequestDto requestDto) {
		ValidationUtils.validateObject(requestDto);
		return notificationGroupService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.NotificationGroup.UPDATE)
	public NotificationGroupDto update(@RequestBody NotificationGroupUpdateRequestDto requestDto) {
		return notificationGroupService.update(requestDto);
	}

	@GetMapping(TimApiPath.NotificationGroup.GET_BY_CODE)
	public NotificationGroupDto getOne(@PathParam("code") String code) {
		return notificationGroupService.getOne(code);
	}

	@GetMapping(TimApiPath.NotificationGroup.GET_ALL)
	public List<NotificationGroupDto> getAll(@RequestParam("status") boolean status) {
		return notificationGroupService.getAll(status);
	}
}
