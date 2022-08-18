package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.subject.SubjectDto;

public interface SubjectService {

    ResponseDto create(SubjectDto dto);

    ResponseDto update(SubjectDto dto);

    ResponseDto getOne(String code);

    void toggleStatus(Long id);
}
