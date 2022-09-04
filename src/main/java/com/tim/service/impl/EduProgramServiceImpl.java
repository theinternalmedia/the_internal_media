package com.tim.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.EducationProgramConverter;
import com.tim.converter.EducationProgramSubjectConverter;
import com.tim.data.ETimMessages;
import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramRequestDto;
import com.tim.dto.educationprogram.EducationProgramUpdateDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectDto;
import com.tim.entity.EducationProgram;
import com.tim.entity.EducationProgramSubject;
import com.tim.entity.Faculty;
import com.tim.entity.SchoolYear;
import com.tim.entity.Subject;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.EducationProgramRepository;
import com.tim.repository.EducationProgramSubjectRepository;
import com.tim.repository.FacultyRepository;
import com.tim.repository.SchoolYearRepository;
import com.tim.repository.SubjectRepository;
import com.tim.service.EduProgramService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.ValidationUtils;

@Service
public class EduProgramServiceImpl implements EduProgramService {
	private static final String EDUCATION_PROGRAM = "Chương trình học";
	private static final String FACULTY = "Khoa";
	private static final String SCHOOL_YEAR = "Niên khóa";
	private static final String SUBJECT = "Môn học";
	
	@Autowired
	EducationProgramRepository eduProgramRepository;
	@Autowired
	EducationProgramConverter eduProgramConverter;
	@Autowired
	EducationProgramSubjectConverter eduProgramSubjectConverter;
	@Autowired
	EducationProgramSubjectRepository eduProgramSubjectRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	SchoolYearRepository schoolYearRepository;
	@Autowired
	FacultyRepository facultyRepository;
	@Autowired
	ExcelService excelService;
	
	
	@Override
	@Transactional
	public EducationProgramDto create(EducationProgramRequestDto education, MultipartFile file) {
		List<EducationProgramSubjectDto> eduProgramSubjectDtos = 
													excelService.getListObjectFromExcelFile(
													file, EducationProgramSubjectDto.class);
		ValidationUtils.validateObject(eduProgramSubjectDtos);
		ValidationUtils.validateObject(education);
		
		if(eduProgramRepository.existsByCode(education.getCode())) {
										throw new TimException(ETimMessages.ALREADY_EXISTS, 
										EDUCATION_PROGRAM, education.getCode());
		}
		//create eduProgram
		EducationProgram eduProgram = eduProgramConverter.toEntity(education);
		setReferenceEntity(eduProgram, education.getSchoolYearCode(), education.getFacultyCode());
		
		//create educationProgram_Subjects
		Set<EducationProgramSubject> eduProgramSubjects = getEduProgramSubjects(
															eduProgramSubjectDtos, eduProgram);
		
		eduProgram.setEducationProgramSubjects(eduProgramSubjects);
		
		EducationProgramDto dto = eduProgramConverter.toDto(eduProgramRepository.save(eduProgram));
		eduProgramSubjectRepository.saveAll(eduProgramSubjects);
		return dto;
	}

	@Override
	@Transactional
	public EducationProgramDto update(EducationProgramUpdateDto updateDto, MultipartFile file) {
		ValidationUtils.validateObject(updateDto);
		
		Long eduProgramId = updateDto.getId();
		EducationProgram oldEduProgram = eduProgramRepository.findById(eduProgramId).orElseThrow(
					() -> new TimNotFoundException(EDUCATION_PROGRAM, "ID", eduProgramId.toString()));
		EducationProgram newEduProgram = eduProgramConverter.toEntity(updateDto, oldEduProgram);
		setReferenceEntity(newEduProgram, updateDto.getSchoolYearCode(), updateDto.getFacultyCode());
		
		//update new Subject of eduProgram(update middle table)
		Set<EducationProgramSubject> eduProgramSubjects = new HashSet<>();
		if (file != null) {
			List<EducationProgramSubjectDto> eduProgramSubjectDtos = excelService.getListObjectFromExcelFile(
					file,EducationProgramSubjectDto.class);
			ValidationUtils.validateObject(eduProgramSubjectDtos);
			
			//delete all old eduProgramSubjects relevant to new update
			List<EducationProgramSubject> oldEduProgramSubjects = eduProgramSubjectRepository
					.findBySemesterAndEducationProgram_Id(updateDto.getSemester(), eduProgramId);			
			eduProgramSubjectRepository.deleteAll(oldEduProgramSubjects);
			
			eduProgramSubjects = getEduProgramSubjects(eduProgramSubjectDtos, newEduProgram);
			newEduProgram.setEducationProgramSubjects(eduProgramSubjects);
		}
		EducationProgramDto dto = eduProgramConverter.toDto(eduProgramRepository.save(newEduProgram));
		if (file != null) {
			eduProgramSubjectRepository.saveAll(eduProgramSubjects);
		}
		return dto;
	}


	@Override
	public EducationProgramDto getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long toggleStatus(Long id) {
		EducationProgram eduProgram = eduProgramRepository.findById(id).orElseThrow(
				() -> new TimNotFoundException(EDUCATION_PROGRAM, "ID", id.toString()));
		eduProgram.setStatus(!eduProgram.getStatus());
		eduProgramRepository.save(eduProgram);
		return id;
	}


	@Override
	public EducationProgramSubjectDto getBySemesterAndEduProgramCode(String semester, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setReferenceEntity(EducationProgram entity, String schoolYearCode, String facultyCode) {
		// set SchoolYear to entity
		SchoolYear schoolYear = schoolYearRepository.getByCode(schoolYearCode)
				.orElseThrow(() -> new TimNotFoundException(SCHOOL_YEAR, "Mã niên khóa", schoolYearCode));
		entity.setSchoolYear(schoolYear);
		
		// set Faculty to entity
		Faculty faculty = facultyRepository.getByCode(facultyCode)
				.orElseThrow(() -> new TimNotFoundException(FACULTY, "Mã khoa", facultyCode));
		entity.setFaculty(faculty);
	}
	
	private Set<EducationProgramSubject> getEduProgramSubjects(
					List<EducationProgramSubjectDto> eduProgramSubjectDtos,EducationProgram eduProgram) {
		Set<EducationProgramSubject> eduProgramSubjects = new HashSet<>();
		for(EducationProgramSubjectDto dto : eduProgramSubjectDtos) {
			Subject subject = subjectRepository.getByCode(dto.getSubjectCode()).orElseThrow(
					() -> new TimNotFoundException(SUBJECT, "Mã môn học", dto.getSubjectCode()));
			
			EducationProgramSubject eduProgramSubject = eduProgramSubjectConverter.toEntity(dto);
			eduProgramSubject.setSubject(subject);
			eduProgramSubject.setEducationProgram(eduProgram);
			
			eduProgramSubjects.add(eduProgramSubject);
		}
		return eduProgramSubjects;
	}
}
