package com.tim.converter;

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

    @Override
	public
    StudentDto toDto(Student entity) {
    	StudentDto model = this.modelMapper.map(entity, StudentDto.class);
        return model;
    }

    @Override
	public Student toEntity(StudentDto dto) {
        return this.modelMapper.map(dto, Student.class);
    }
}
