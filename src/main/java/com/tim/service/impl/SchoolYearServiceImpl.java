package com.tim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.SchoolYearConverter;
import com.tim.data.ETimMessages;
import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.dto.schoolyear.SchoolYearRequestDto;
import com.tim.dto.schoolyear.SchoolYearUpdateRequestDto;
import com.tim.entity.SchoolYear;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.SchoolYearRepository;
import com.tim.service.SchoolYearService;
import com.tim.utils.ValidationUtils;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {
	private static final String SCHOOL_YEAR = "Khóa Học";
	@Autowired
	private SchoolYearConverter schoolYearConverter;
	@Autowired
	private SchoolYearRepository schoolYearRepository;
	@Override
	public SchoolYearDto create(SchoolYearRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		// Check exists code
		if (schoolYearRepository.existsByCode(requestDto.getCode())) {
			throw new TimException(ETimMessages.ALREADY_EXISTS, "Mã Khóa Học", requestDto.getCode());
		}
		SchoolYear schoolYear = schoolYearConverter.toEntity(requestDto);
		return schoolYearConverter.toDto(schoolYearRepository.save(schoolYear));
	}
	
    @Override
    public SchoolYearDto update(SchoolYearUpdateRequestDto requestDto) {
    	SchoolYear schoolYear = schoolYearRepository.findById(requestDto.getId()).orElseThrow(
    			() -> new TimNotFoundException(SCHOOL_YEAR, "ID", requestDto.getId().toString()));
    	if (!schoolYear.getCode().equals(requestDto.getCode())){
    		if (schoolYearRepository.existsByCode(requestDto.getCode())) {
    			throw new TimException(ETimMessages.ALREADY_EXISTS, "Mã Khóa Học", requestDto.getCode());
    		}
    	}
    	schoolYear.setCode(requestDto.getCode());
    	schoolYear.setName(requestDto.getName());
    	schoolYear = schoolYearRepository.save(schoolYear);
    	return schoolYearConverter.toDto(schoolYear);
    }

	@Override
	public SchoolYearDto getOne(String code) {
		SchoolYear schoolYear = schoolYearRepository.findByCode(code).orElseThrow(
				() -> new TimNotFoundException(SCHOOL_YEAR, "Mã Khóa Học", code));
		return schoolYearConverter.toDto(schoolYear);
	}

    @Override
	public Long toggleStatus(Long id) {
		SchoolYear schoolYear = schoolYearRepository.findById(id).orElseThrow(
				() -> new TimNotFoundException(SCHOOL_YEAR, "Mã Khóa Học", id.toString()));
		schoolYear.setStatus(!schoolYear.getStatus());
		schoolYearRepository.save(schoolYear);
		return id;
	}
}
