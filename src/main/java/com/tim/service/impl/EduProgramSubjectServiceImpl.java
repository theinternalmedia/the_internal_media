package com.tim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.EducationProgramSubjectConverter;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectUpdateDto;
import com.tim.entity.EducationProgramSubject;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.EducationProgramSubjectRepository;
import com.tim.service.EduProgramSubjectService;
import com.tim.utils.ValidationUtils;

@Service
public class EduProgramSubjectServiceImpl implements EduProgramSubjectService{
	private static final String EDUPROGRAM_SUBJECT = "Dữ liệu";
	@Autowired
	EducationProgramSubjectRepository eduProgramSubjectRepository;
	@Autowired
	EducationProgramSubjectConverter eduProgramSubjectConverter;
	
	
	@Override
	public EducationProgramSubjectDto update(EducationProgramSubjectUpdateDto updateDto) {
		ValidationUtils.validateObject(updateDto);
		
		Long id = updateDto.getId();
		EducationProgramSubject entity = eduProgramSubjectRepository
				.getOneById(id).orElseThrow(
				() -> new TimNotFoundException(EDUPROGRAM_SUBJECT, "ID", String.valueOf(id)));
		entity = eduProgramSubjectConverter.toEntity(updateDto, entity);
		
		return eduProgramSubjectConverter.toDto(eduProgramSubjectRepository.save(entity));
	}


	@Override
	public EducationProgramSubjectDto getOneBySubjectCodeAndEduProgramCode(
										String subjectCode, String eduProgramCode) {
		EducationProgramSubject entity = eduProgramSubjectRepository
				.findBySubject_CodeAndEducationProgram_Code(subjectCode, eduProgramCode)
				.orElseThrow(() -> new TimNotFoundException(EDUPROGRAM_SUBJECT, 
				"Mã môn học, Mã chương trình học", subjectCode + ", " + eduProgramCode));
		
		return eduProgramSubjectConverter.toDto(entity);
	}
}
