package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.schoolyear.SchoolYearDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public interface SchoolYearService {

    ResponseDto create(@Valid SchoolYearDto dto);

    ResponseDto update(@Valid SchoolYearDto dto);

    ResponseDto getOne(@NotEmpty String code);

    void toggleStatus(@NotBlank Long id);
}
