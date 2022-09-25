package com.tim.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.subject.SubjectDto;
import com.tim.dto.subject.SubjectCreateDto;
import com.tim.dto.subject.SubjectUpdateDto;

public interface SubjectService {

    SubjectDto create(SubjectCreateDto requestDto);

	SubjectDto update(SubjectUpdateDto requestDto);

	long create(MultipartFile file);
	
	long toggleStatus(Set<Long> ids);

}
