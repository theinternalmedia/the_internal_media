package com.tim.restful;


import java.util.Set;

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

import com.tim.data.Permissions;
import com.tim.data.TimApiPath;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.news.NewsCreateDto;
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsPageRequestDto;
import com.tim.dto.news.NewsUpdateDto;
import com.tim.service.NewsService;

@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class NewsResource {

	@Autowired
	private NewsService newsService;

	@PreAuthorize("hasAuthority('" + Permissions.News.CREATE + "')")
	@PostMapping(value = TimApiPath.News.CREATE)
	public NewsDto create(
			@RequestPart(value = "file", required = false) MultipartFile file,
			NewsCreateDto requestDto) {
		
		return newsService.create(requestDto, file);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.News.UPDATE + "')")
	@PutMapping(value = TimApiPath.News.UPDATE)
	public NewsDto update(
			@RequestPart(value = "file", required = false) MultipartFile file,
			NewsUpdateDto updateDto) {
		
		return newsService.update(updateDto, file);
	}

	@GetMapping(value = TimApiPath.News.GET_BY_ID)
	public NewsDto getById(@PathVariable("id") Long id) {
		return newsService.getOne(id);
	}
	
	@GetMapping(value = TimApiPath.News.GET_BY_SLUG)
	public NewsDto getBySlug(@RequestParam("slug") String slug) {
		return newsService.getOne(slug);
	}
	
	@GetMapping(value = TimApiPath.News.GET_PAGE)
	public PagingResponseDto getPage(NewsPageRequestDto pageRequestDto) {
		return newsService.getPage(pageRequestDto);
	}

	@PreAuthorize("hasAuthority('" + Permissions.News.TOGGLE_STATUS + "')")
	@PutMapping(value = TimApiPath.News.TOGGLE_STATUS)
	public long toggleStatus(@RequestParam Set<Long> ids) {
		return newsService.toggleStatus(ids);
	}
}
