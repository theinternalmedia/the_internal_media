package com.tim.service.impl;

import com.tim.converter.TeacherConverter;
import com.tim.dto.teacher.TeacherDto;
import com.tim.entity.Teacher;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherConverter teacherConverter;


    @Override
    public long save(TeacherDto teacherDto) {
        Teacher entity = teacherConverter.toEntity(teacherDto);
        return teacherRepository.save(entity).getId();
    }
}
