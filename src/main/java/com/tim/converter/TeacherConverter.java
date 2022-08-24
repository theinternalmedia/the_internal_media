package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherRequestDto;
import com.tim.dto.teacher.TeacherUpdateRequestDto;
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
		return this.modelMapper.map(entity, TeacherDto.class);
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

	public Teacher toEntity(TeacherRequestDto requestDto) {
		return this.modelMapper.map(requestDto, Teacher.class);
	}

	public Teacher toEntity(TeacherUpdateRequestDto requestDto, Teacher entity) {
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
}
