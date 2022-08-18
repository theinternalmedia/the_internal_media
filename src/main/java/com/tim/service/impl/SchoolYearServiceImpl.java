package com.tim.service.impl;

import com.tim.converter.SchoolYearConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.entity.SchoolYear;
import com.tim.repository.SchoolYearRepository;
import com.tim.service.SchoolYearService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {
	@Autowired
	private SchoolYearConverter schoolYearConverter;
	@Autowired
	private SchoolYearRepository schoolYearRepository;
	@Override
	public ResponseDto insert(SchoolYearDto dto) {
		SchoolYear schoolYear = schoolYearConverter.toEntity(dto);
		return new ResponseDto(schoolYearConverter.toDto(schoolYearRepository.save(schoolYear)));
	}
	
}
