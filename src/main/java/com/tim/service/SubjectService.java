package com.tim.service;

import com.tim.dto.subject.SubjectDto;

public interface SubjectService {

    SubjectDto create(SubjectDto dto);

    SubjectDto update(SubjectDto dto);

    SubjectDto getOne(String code);

    void toggleStatus(Long id);
}
