package com.tim.restful;


import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;
import com.tim.dto.news.NewsUpdateDto;
import com.tim.service.NewsService;
import com.tim.utils.Utility;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class NewsResource {

	@Autowired
	private NewsService newsService;


	@PostMapping(value = TimApiPath.News.CREATE)
	public NewsDto create(@RequestPart(
			value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NewsRequestDto in String Json, FacultyCodes can be empty", 
			example = TimConstants.ApiParamExample.News.CREATE_newsRequestDtoJson) 
			@RequestParam("newsRequestDtoJson") String newsRequestDtoJson) {
		
		NewsRequestDto requestDto = Utility.convertStringJsonToObject(newsRequestDtoJson,
				new TypeReference<NewsRequestDto>() {
				});
		return newsService.create(requestDto, file);
	}
	
	@PutMapping(value = TimApiPath.News.UPDATE)
	public NewsDto update(
			@RequestPart(value = "file", required = false) MultipartFile file,
			@ApiParam(value = "NewsRequestDto in String Json, FacultyCodes can be empty", 
			example = TimConstants.ApiParamExample.News.UPDATE_newsRequestDtoJson) 
			@RequestParam("newsRequestDtoJson") String newsRequestDtoJson) {
		
		NewsUpdateDto requestDto = Utility.convertStringJsonToObject(newsRequestDtoJson,
				new TypeReference<NewsUpdateDto>() {
				});
		return newsService.update(requestDto, file);
	}

	@GetMapping(value = TimApiPath.News.GET_BY_ID)
	public NewsDto getById(@PathVariable("id") Long id) {
		return newsService.getOne(id);
	}
	
	@GetMapping(value = TimApiPath.News.GET_BY_SLUG)
	public NewsDto getBySlug(@PathParam("slug") String slug) {
		return newsService.getOne(slug);
	}
	
	@GetMapping(value = TimApiPath.News.GET_PAGE)
	public ResponseEntity<PagingResponseDto> getPage(
			@RequestParam("page") int page, 
			@RequestParam("size") int size,
			@RequestParam("status") boolean status,
			@PathParam("id") Long id,
			@PathParam("search") String search,
			@PathParam("facultyCode") String facultyCode) {
		return ResponseEntity.ok(newsService.getPage(page, size, status, id, search, facultyCode));
	}

	@PutMapping(value = TimApiPath.News.TOGGLE_STATUS)
	public long toggleStatus(@RequestParam Set<Long> ids) {
		return newsService.toggleStatus(ids);
	}
}
