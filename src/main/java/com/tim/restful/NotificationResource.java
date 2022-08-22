package com.tim.restful;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.tim.data.TimApiPath;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.dto.notification.NotificationUpdateRequestDto;
import com.tim.service.NotificationService;
import com.tim.utils.PrincipalUtils;
import com.tim.utils.Utility;

import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class NotificationResource {

	@Autowired
	private NotificationService notificationService;

	@PostMapping(value = TimApiPath.Notification.CREATE)
	public ResponseEntity<ResponseDto> create(@RequestPart(
			value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NotificationRequestDto in String Json.", 
			example = TimConstants.ApiParamExample.Notification.CREATE_notifyRequestDtoJson) 
			@RequestParam("notifyRequestDtoJson") String notifyRequestDtoJson) {
		
		NotificationRequestDto requestDto = 
				Utility.convertStringJsonToObject(
						notifyRequestDtoJson, 
						new TypeReference<NotificationRequestDto>() {});
		return ResponseEntity.ok(notificationService.create(requestDto, file));
	}

	@PutMapping(value = TimApiPath.Notification.UPDATE)
	public ResponseEntity<ResponseDto> update(@RequestPart(
			value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NotificationRequestDto in String Json.", 
			example = TimConstants.ApiParamExample.Notification.UPDATE_notifyRequestDtoJson) 
			@RequestParam("notifyUpdateRequestDtoJson") String notifyUpdateRequestDtoJson) {
		
		NotificationUpdateRequestDto requestDto = 
				Utility.convertStringJsonToObject(
						notifyUpdateRequestDtoJson, 
						new TypeReference<NotificationUpdateRequestDto>() {});
		return ResponseEntity.ok(notificationService.update(requestDto, file));
	}
	
	@GetMapping(TimApiPath.Notification.GET_BY_ID)
	public ResponseDto getById(@PathVariable("id") Long id) {
		return notificationService.getOne(id);
	}
	
	@GetMapping(TimApiPath.Notification.GET_BY_SLUG)
	public ResponseDto getBySlug(@PathParam("slug") String slug) {
		return notificationService.getOne(slug);
	}

	@GetMapping(TimApiPath.Notification.GET_PAGE)
	public PagingResponseDto getPage(@RequestParam("page") int page, 
			@RequestParam("size") int size) {
		return notificationService.getPage(page, size, 
				PrincipalUtils.getAuthenticatedUsersUserId(),
				PrincipalUtils.authenticatedUserIsTeacher());
	}
}
