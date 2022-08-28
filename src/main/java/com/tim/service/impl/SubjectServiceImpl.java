package com.tim.service.impl;

import org.springframework.stereotype.Service;

import com.tim.dto.subject.SubjectDto;
import com.tim.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Override
    public SubjectDto create(SubjectDto dto) {
        return null;
    }

    @Override
    public SubjectDto update(SubjectDto dto) {
        return null;
    }

    @Override
    public SubjectDto getOne(String code){
        return null;
    }

    @Override
    public void toggleStatus(Long id) {

    }
}
