package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService {

    ResponseDto create( String newsDtoJsonRequest, MultipartFile image);

    ResponseDto update(NewsDto newsDto);

    ResponseDto getOne(Long id);

    ResponseDto toogleStatus(Long id);

	ResponseDto getPage(int page, int size, boolean status, String facultyCode);

}
