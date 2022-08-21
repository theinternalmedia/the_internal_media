package com.tim.restful;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;
import com.tim.service.NewsService;
import com.tim.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class NewsResource {

	@Autowired
	private NewsService newsService;


	@PostMapping(value = TimApiPath.News.CREATE)
	public ResponseEntity<ResponseDto> create(
					@RequestPart(value = "image", required = false) MultipartFile image,
					@RequestParam("newsRequestDtoJson") String newsDtoJsonRequest) {
		/*NewsRequestDto requestDto = Utility.convertStringJsonToObject(newsDtoJsonRequest,
									new TypeReference<NewsRequestDto>() {});*/
//		requestDto.setThumbnailFile(image);
		try {
			return ResponseEntity.ok(newsService.create(newsDtoJsonRequest, image));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseDto("Save image unsuccess"));
		}
	}

	@PutMapping(value = TimApiPath.News.UPDATE)
	public ResponseEntity<ResponseDto> update(@RequestBody NewsDto newsDto) {
		return ResponseEntity.ok(newsService.update(newsDto));
	}

	@GetMapping(value = TimApiPath.News.GET_ONE)
	public ResponseEntity<ResponseDto> getOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(newsService.getOne(id));
	}
	
	@GetMapping(value = TimApiPath.News.GET_PAGE)
	public ResponseEntity<ResponseDto> getPage(@RequestParam("page") int page, 
			@RequestParam("size") int size,
			@RequestParam("status") boolean status,
			@PathParam("facultyCode") String facultyCode) {
		return ResponseEntity.ok(newsService.getPage(page, size, status, facultyCode));
	}

	@DeleteMapping(value = TimApiPath.News.DELETE)
	public ResponseEntity<ResponseDto> delete(@RequestParam Long id) {
		return ResponseEntity.ok(newsService.toogleStatus(id));
	}
}
