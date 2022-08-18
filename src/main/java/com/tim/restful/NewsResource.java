package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.service.NewsService;

@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class NewsResource {

	@Autowired
	private NewsService newsService;

	@PostMapping(value = TimApiPath.News.INSERT)
	public ResponseEntity<ResponseDto> create(@RequestBody NewsDto newsDto) {
		return ResponseEntity.ok(newsService.create(newsDto));
	}

	@PutMapping(value = TimApiPath.News.UPDATE)
	public ResponseEntity<ResponseDto> update(@RequestBody NewsDto newsDto) {
		return ResponseEntity.ok(newsService.update(newsDto));
	}

	@GetMapping(value = TimApiPath.News.GET_ONE)
	public ResponseEntity<ResponseDto> getOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(newsService.getOne(id));
	}

	@DeleteMapping(value = TimApiPath.News.DELETE)
	public ResponseEntity<ResponseDto> delete(@RequestParam Long id) {
		return ResponseEntity.ok(newsService.toogleStatus(id));
	}
}
