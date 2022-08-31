package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.subject.SubjectDto;
import com.tim.dto.subject.SubjectRequestDto;
import com.tim.dto.subject.SubjectUpdateRequestDto;

public interface SubjectService {

    SubjectDto create(SubjectRequestDto requestDto);

	SubjectDto update(SubjectUpdateRequestDto requestDto);

	long create(MultipartFile file);

}
