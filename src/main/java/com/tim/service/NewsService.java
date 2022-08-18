package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface NewsService {

    ResponseDto create(@Valid NewsDto newsDto);

    ResponseDto update(@Valid NewsDto newsDto);

    ResponseDto getOne(@NotBlank Long id);

    ResponseDto toogleStatus(@NotBlank Long id);

}
