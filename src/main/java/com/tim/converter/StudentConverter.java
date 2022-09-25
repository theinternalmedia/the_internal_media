package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.UserUpdateProfileDto;
import com.tim.dto.UserUpdateRequestDto;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentCreateDto;
import com.tim.dto.student.StudentResponseDto;
import com.tim.dto.student.StudentUpdateProfileDto;
import com.tim.dto.student.StudentUpdateDto;
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
		StudentDto dto = this.modelMapper.map(entity, StudentDto.class);
		dto.setClassCode(entity.getClassz().getCode());
		return dto;
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

	public Student toEntity(StudentCreateDto requestDto) {
		return this.modelMapper.map(requestDto, Student.class);
	}
	
	public StudentUpdateDto toDto(UserUpdateRequestDto updateRequestDto) {
		return this.modelMapper.map(updateRequestDto, StudentUpdateDto.class);
	}

	public Student toEntity(StudentUpdateDto requestDto, Student student) {
		student.setAddress(requestDto.getAddress());
		student.setDob(requestDto.getDob());
		student.setEmail(requestDto.getEmail());
		student.setGender(requestDto.getGender());
		student.setName(requestDto.getName());
		student.setPhone(requestDto.getPhone());
		student.setUserId(requestDto.getUserId());
		return student;
	}
	
	public StudentUpdateProfileDto toDto(UserUpdateProfileDto userDto) {
		return this.modelMapper.map(userDto, StudentUpdateProfileDto.class);
	}
	
	public Student toEntity(StudentUpdateProfileDto dto, Student entity) {
		entity.setEmail(dto.getEmail());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setDob(dto.getDob());
		entity.setGender(dto.setGender());
		
		return entity;
	}
	
	public StudentResponseDto toResponseDto(Student entity) {
		StudentResponseDto dto = this.modelMapper.map(entity, StudentResponseDto.class);
		dto.setClassCode(entity.getClassz().getCode());
		dto.setClassName(entity.getClassz().getName());
		dto.setPassword(null);
		return dto;
	}

	public List<StudentResponseDto> toResponseDtoList(List<Student> entityList) {
		List<StudentResponseDto> result = new ArrayList<>();
		entityList.forEach(item -> {
			result.add(toResponseDto(item));
		});
		return result;
	}
}
