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
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsRequestDto;
import com.tim.dto.news.NewsUpdateDto;
import com.tim.service.NewsService;
import com.tim.utils.Utility;

import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class NewsResource {

	@Autowired
	private NewsService newsService;

	@PostMapping(value = TimApiPath.News.CREATE)
	public ResponseEntity<ResponseDto> create(@RequestPart(
			value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NewsRequestDto in String Json, FacultyCodes can be empty", 
			example = TimConstants.ApiParamExample.News.CREATE_newsRequestDtoJson) 
			@RequestParam("newsRequestDtoJson") String newsRequestDtoJson) {
		
		NewsRequestDto requestDto = Utility.convertStringJsonToObject(newsRequestDtoJson,
				new TypeReference<NewsRequestDto>() {
				});
		requestDto.setThumbnailFile(file);
		return ResponseEntity.ok(newsService.create(requestDto));
	}
	
	@PutMapping(value = TimApiPath.News.UPDATE)
	public ResponseEntity<ResponseDto> update(
			@RequestPart(value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NewsRequestDto in String Json, FacultyCodes can be empty", 
			example = TimConstants.ApiParamExample.News.UPDATE_newsRequestDtoJson) 
			@RequestParam("newsRequestDtoJson") String newsRequestDtoJson) {
		
		NewsUpdateDto requestDto = Utility.convertStringJsonToObject(newsRequestDtoJson,
				new TypeReference<NewsUpdateDto>() {
				});
		requestDto.setThumbnailFile(file);
		return ResponseEntity.ok(newsService.update(requestDto));
	}

	@GetMapping(value = TimApiPath.News.GET_BY_ID)
	public ResponseEntity<ResponseDto> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(newsService.getOne(id));
	}
	
	@GetMapping(value = TimApiPath.News.GET_BY_SLUG)
	public ResponseEntity<ResponseDto> getBySlug(@PathParam("slug") String slug) {
		return ResponseEntity.ok(newsService.getOne(slug));
	}
	
	@GetMapping(value = TimApiPath.News.GET_PAGE)
	public ResponseEntity<ResponseDto> getPage(
			@RequestParam("page") int page, 
			@RequestParam("size") int size,
			@RequestParam("status") boolean status,
			@PathParam("id") Long id,
			@PathParam("search") String search,
			@PathParam("facultyCode") String facultyCode) {
		return ResponseEntity.ok(newsService.getPage(page, size, status, id, search, facultyCode));
	}

	@PutMapping(value = TimApiPath.News.TOGGLE_STATUS)
	public ResponseEntity<ResponseDto> toggleStatus(@PathVariable("id") Long id) {
		return ResponseEntity.ok(newsService.toogleStatus(id));
	}
}
