package com.tim.restful;

import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.data.Permissions;
import com.tim.data.TimApiPath;
import com.tim.data.TimConstants.ApiParamExample.Notification;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.dto.notification.NotificationPageRequestDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.dto.notification.NotificationUpdateRequestDto;
import com.tim.service.NotificationService;
import com.tim.utils.Utility;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class NotificationResource {

	@Autowired
	private NotificationService notificationService;

	@PreAuthorize("hasAuthority('" + Permissions.Notification.CREATE + "')")
	@PostMapping(value = TimApiPath.Notification.CREATE)
	public NotificationDto create(@RequestPart(
			value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NotificationRequestDto in String Json.", 
				example = Notification.CREATE_notifyRequestDtoJson) 
			@RequestParam("notifyRequestDtoJson") String notifyRequestDtoJson) {
		
		NotificationRequestDto requestDto = 
				Utility.convertStringJsonToObject(
						notifyRequestDtoJson, 
						new TypeReference<NotificationRequestDto>() {});
		return notificationService.create(requestDto, file);
	}

	@PreAuthorize("hasAuthority('" + Permissions.Notification.UPDATE + "')")
	@PutMapping(value = TimApiPath.Notification.UPDATE)
	public NotificationDto update(@RequestPart(
			value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NotificationRequestDto in String Json.", 
				example = Notification.UPDATE_notifyRequestDtoJson) 
			@RequestParam("notifyUpdateRequestDtoJson") String notifyUpdateRequestDtoJson) {
		
		NotificationUpdateRequestDto requestDto = 
				Utility.convertStringJsonToObject(
						notifyUpdateRequestDtoJson, 
						new TypeReference<NotificationUpdateRequestDto>() {});
		return notificationService.update(requestDto, file);
	}
	
	@GetMapping(TimApiPath.Notification.GET_BY_ID)
	public NotificationDto getById(@PathVariable("id") Long id) {
		return notificationService.getOne(id);
	}
	
	@GetMapping(TimApiPath.Notification.GET_BY_SLUG)
	public NotificationDto getBySlug(@PathParam("slug") String slug) {
		return notificationService.getOne(slug);
	}

	@ApiOperation(value = "Get Paging Notification ", 
			notes = "Use for Admin")
	@PreAuthorize("hasAuthority('" + Permissions.Notification.READ + "')")
	@GetMapping(TimApiPath.Notification.GET_PAGE_ADMIN)
	public PagingResponseDto getPageAdmin(NotificationPageRequestDto pageRequestDto) {
		return notificationService.getPage(pageRequestDto, true);
	}
	
	@ApiOperation(value = "Get Paging Notification ", 
			notes = "Use for users is not Admin or Anonymous")
	@GetMapping(TimApiPath.Notification.GET_PAGE)
	public PagingResponseDto getPage(NotificationPageRequestDto pageRequestDto) {
		return notificationService.getPage(pageRequestDto, false);
	}
	
	@PutMapping(TimApiPath.Notification.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return notificationService.toggleStatus(ids);
	}
}
