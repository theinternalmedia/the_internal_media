package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.UserUpdateRequestDto;
import com.tim.dto.UserUpdateUserDto;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.dto.student.StudentUpdateRequestDto;
import com.tim.dto.student.StudentUpdateStudentDto;
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
	
	public StudentUpdateRequestDto toDto(UserUpdateRequestDto updateRequestDto) {
		return this.modelMapper.map(updateRequestDto, StudentUpdateRequestDto.class);
	}

	public Student toEntity(StudentUpdateRequestDto requestDto, Student student) {
		student.setAddress(requestDto.getAddress());
		student.setDob(requestDto.getDob());
		student.setEmail(requestDto.getEmail());
		student.setGender(requestDto.getGender());
		student.setName(requestDto.getName());
		student.setPhone(requestDto.getPhone());
		student.setUserId(requestDto.getUserId());
		return student;
	}
	
	public StudentUpdateStudentDto toDto(UserUpdateUserDto userDto) {
		return this.modelMapper.map(userDto, StudentUpdateStudentDto.class);
	}
	
	public Student toEntity(StudentUpdateStudentDto dto, Student entity) {
		entity.setEmail(dto.getEmail());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setDob(dto.getDob());
		entity.setGender(dto.setGender());
		
		return entity;
	}

}
