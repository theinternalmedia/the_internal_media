package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.dto.marks.MarksDto;
import com.tim.dto.marks.MarksRequestDto;
import com.tim.service.MarksService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class MarksResource {
	
	@Autowired
	private MarksService marksService;
	
	@PostMapping(TimApiPath.Marks.CREATE)
	public MarksDto create(@RequestBody MarksRequestDto requestDto) {
		return marksService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.Marks.UPLOAD_EXCEL)
	public long update(@RequestPart("file") MultipartFile file) {
		return marksService.create(file);
	} 
}
