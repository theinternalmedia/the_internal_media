package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.entity.Student;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class StudentConverter extends AbstractConverter<StudentDto, Student> {

	@Override
	public StudentDto toDto(Student entity) {
		StudentDto model = this.modelMapper.map(entity, StudentDto.class);
		return model;
	}

	@Override
	public Student toEntity(StudentDto dto) {
		return this.modelMapper.map(dto, Student.class);
	}

	@Override
	public List<StudentDto> toDtoList(List<Student> entityList) {
		List<StudentDto> result = new ArrayList<>();
		entityList.forEach(item -> {
			result.add(toDto(item));
		});
		return result;
	}

	public Student toEntity(StudentRequestDto requestDto) {
		return this.modelMapper.map(requestDto, Student.class);
	}
}
