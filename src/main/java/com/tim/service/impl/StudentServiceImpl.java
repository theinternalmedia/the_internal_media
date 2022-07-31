package com.tim.service.impl;

import com.tim.converter.StudentConverter;
import com.tim.dto.student.StudentDto;
import com.tim.entity.Student;
import com.tim.repository.StudentRepository;
import com.tim.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentConverter studentConverter;

    @Override
    public long save(StudentDto studentDto) {
        Student entity = studentConverter.toEntity(studentDto);
        return studentRepository.save(entity).getId();
    }
}
