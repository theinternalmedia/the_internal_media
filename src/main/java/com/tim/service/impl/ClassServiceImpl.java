package com.tim.service.impl;

import com.tim.converter.ClassConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.classes.ClassDto;
import com.tim.entity.Class;
import com.tim.entity.Teacher;
import com.tim.repository.ClassRepository;
import com.tim.service.ClassService;
import com.tim.utils.Utility;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ClassConverter classConverter;
	@Override
	public ResponseDto save(ClassDto classDto) {
		Class entity = classConverter.toEntity(classDto);
		return new ResponseDto(classConverter.toDto(classRepository.save(entity)));
	 }
	
	@Override
	public ResponseDto findByUserId(@NotBlank String classId) {
		Class entity = classRepository.findByClassId(classId).orElse(null);
		if(entity == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND, 
					TimConstants.ActualEntityName.CLASS, "Mã LỚP", classId));
		}
		return new ResponseDto(classConverter.toDto(entity));
	}

	@Override
	public ResponseDto update(ClassDto classDto, long id) {
		Class entity = classRepository.findByClassId(String.valueOf(id)).orElse(null);
		if(entity == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND, 
					TimConstants.ActualEntityName.CLASS, "Mã LỚP", String.valueOf(id)));
		}
		return new ResponseDto(classConverter.toDto(classRepository.save(entity)));
	}

}