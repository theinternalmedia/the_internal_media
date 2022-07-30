package com.tim.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim.dto.student.StudentDto;
import com.tim.entity.Student;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class StudentConverter extends AbstractConverter<StudentDto, Student> {
    /**
     * thinhnguyen
     */

    @Autowired
    private ModelMapper modelMapper;

    @Override
	public
    StudentDto toDto(Student entity) {
    	StudentDto model = modelMapper.map(entity, StudentDto.class);
        return model;
    }

    @Override
	public Student toEntity(StudentDto dto) {
        return modelMapper.map(dto, Student.class);
    }
}
