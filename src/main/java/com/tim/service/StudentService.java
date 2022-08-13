package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentDto;

public interface StudentService {

    ResponseDto create(StudentDto dto);

    ResponseDto update(StudentDto dto);

    ResponseDto getOne(Long id);

    ResponseDto getByUserName(String name);

    ResponseDto getByEmail(String email);

    ResponseDto toggleStatus(Long id);
}
