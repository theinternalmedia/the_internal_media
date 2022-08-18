package com.tim.service.impl;

import com.tim.dto.ResponseDto;
import com.tim.dto.subject.SubjectDto;
import org.springframework.stereotype.Service;

import com.tim.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Override
    public ResponseDto create(SubjectDto dto) {
        return null;
    }

    @Override
    public ResponseDto update(SubjectDto dto) {
        return null;
    }

    @Override
    public ResponseDto getOne(String code){
        return null;
    }

    @Override
    public void toggleStatus(Long id) {

    }
}
