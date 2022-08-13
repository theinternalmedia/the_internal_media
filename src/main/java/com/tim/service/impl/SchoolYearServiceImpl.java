package com.tim.service.impl;

import com.tim.converter.SchoolYearConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.repository.SchoolYearRepository;
import com.tim.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {

    @Autowired
    private SchoolYearRepository schoolYearRepository;
    @Autowired
    private SchoolYearConverter schoolYearConverter;

    @Override
    public ResponseDto create(SchoolYearDto dto) {
        return null;
    }

    @Override
    public ResponseDto update(SchoolYearDto dto) {
        return null;
    }

    @Override
    public ResponseDto getOne(String code) {
        return null;
    }

    @Override
    public void toggleStatus(Long id) {

    }
}
