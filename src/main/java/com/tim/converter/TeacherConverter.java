package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.UserUpdateProfileDto;
import com.tim.dto.UserUpdateRequestDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherCreateDto;
import com.tim.dto.teacher.TeacherResponseDto;
import com.tim.dto.teacher.TeacherUpdateProfileDto;
import com.tim.dto.teacher.TeacherUpdateDto;
import com.tim.entity.Teacher;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class TeacherConverter extends AbstractConverter<TeacherDto, Teacher> {

	@Override
	public TeacherDto toDto(Teacher entity) {
		TeacherDto dto = this.modelMapper.map(entity, TeacherDto.class);
		dto.setFacultyCode(entity.getFaculty().getCode());
		return dto;
	}

	@Override
	public Teacher toEntity(TeacherDto dto) {
		return this.modelMapper.map(dto, Teacher.class);
	}

	@Override
	public List<Teacher> toEntityList(List<TeacherDto> dtoList) {
		List<Teacher> result = new ArrayList<Teacher>();
		dtoList.forEach(item -> {
			result.add(toEntity(item));
		});
		return result;
	}

	@Override
	public List<TeacherDto> toDtoList(List<Teacher> entityList) {
		List<TeacherDto> result = new ArrayList<TeacherDto>();
		entityList.forEach(item -> {
			result.add(toDto(item));
		});
		return result;
	}

	public Teacher toEntity(TeacherCreateDto requestDto) {
		return this.modelMapper.map(requestDto, Teacher.class);
	}
	
	public TeacherUpdateDto toDto(UserUpdateRequestDto userUpdateRequestDto) {
		return this.modelMapper.map(userUpdateRequestDto, TeacherUpdateDto.class);
	}

	public Teacher toEntity(TeacherUpdateDto requestDto, Teacher entity) {
		entity.setAddress(requestDto.getAddress());
		entity.setDob(requestDto.getDob());
		entity.setEmail(requestDto.getEmail());
		entity.setGender(requestDto.getGender());
		entity.setManager(requestDto.isManager());
		entity.setName(requestDto.getName());
		entity.setPhone(requestDto.getPhone());
		entity.setRemark(requestDto.getRemark());
		entity.setUserId(requestDto.getUserId());
		return entity;
	}
	
	public TeacherUpdateProfileDto toDto(UserUpdateProfileDto userDto) {
		return this.modelMapper.map(userDto, TeacherUpdateProfileDto.class);
	}
	
	public Teacher toEntity(TeacherUpdateProfileDto dto, Teacher entity) {
		entity.setEmail(dto.getEmail());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setDob(dto.getDob());
		entity.setGender(dto.setGender());
		
		return entity;
	}

	public TeacherResponseDto toResponseDto(Teacher entity) {
		TeacherResponseDto dto = this.modelMapper.map(entity, TeacherResponseDto.class);
		dto.setFacultyCode(entity.getFaculty().getCode());
		dto.setFacultyName(entity.getFaculty().getName());
		dto.setPassword(null);
		return dto;
	}

	public List<TeacherResponseDto> toResponseDtoList(List<Teacher> entityList) {
		List<TeacherResponseDto> result = new ArrayList<>();
		entityList.forEach(item -> {
			result.add(toResponseDto(item));
		});
		return result;
	}
}
