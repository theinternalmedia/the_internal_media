package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;
import com.tim.dto.news.NewsUpdateDto;

public interface NewsService {

	/**
	 * Create News.
	 * 
	 * @author thinh
	 * @param requestDto
	 * @return ResponseDto
	 */
	NewsDto create(NewsRequestDto requestDto, MultipartFile image);

    /**
     * Update News.
     * 
     * @author thinh
     * @param newsDto
     * @return ResponseDto
     */
	NewsDto update(NewsUpdateDto newsDto, MultipartFile image);

    /**
     * @author minhtuanitk43
     * @param id
     * @return
     */
	NewsDto getOne(Long id);

    /**
     * Toogle status.
     * 
     * @author thinh
     * @param id
     * @return ResponseDto
     */
    Long toogleStatus(Long id);
    
    /**
     * Get news page.
     * 
     * @author minhtuanitk43
     * @param page
     * @param size
     * @param status
     * @param id 
     * @param facultyCode
     * @param facultyCode2 
     * @return
     */
	PagingResponseDto getPage(int page, int size, boolean status, 
			Long id, String search, String facultyCode);

	/**
	 * @author minhtuanitk43
	 * @param slug
	 * @return ResponseDto
	 */
	NewsDto getOne(String slug);

}
