package com.tim.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.EducationProgramConverter;
import com.tim.converter.EducationProgramSubjectConverter;
import com.tim.data.ETimMessages;
import com.tim.data.SearchOperation;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramPageRequestDto;
import com.tim.dto.educationprogram.EducationProgramCreateDto;
import com.tim.dto.educationprogram.EducationProgramResponseDto;
import com.tim.dto.educationprogram.EducationProgramUpdateDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectCreateDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectResponseDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.entity.EducationProgram;
import com.tim.entity.EducationProgramSubject;
import com.tim.entity.EducationProgram_;
import com.tim.entity.Faculty;
import com.tim.entity.Faculty_;
import com.tim.entity.SchoolYear;
import com.tim.entity.SchoolYear_;
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
import com.tim.utils.MessageUtils;
import com.tim.utils.ValidationUtils;

@Service
public class EduProgramServiceImpl implements EduProgramService {
	private static final String EDUCATION_PROGRAM = "Chương Trình Giảng Dạy";
	private static final String FACULTY = "Khoa";
	private static final String SCHOOL_YEAR = "Khóa";
	private static final String SUBJECT = "Môn Học";
	
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
	public EducationProgramDto create(EducationProgramCreateDto education, MultipartFile file) {
		// Validate input 
		ValidationUtils.validateObject(education);
		
		// Get list Subject from file
		List<EducationProgramSubjectCreateDto> eduProgramSubjectDtos = 
				excelService.getListObjectFromExcelFile(file, EducationProgramSubjectCreateDto.class);
		
		// Check unique code
		if (eduProgramRepository.existsByCode(education.getCode())) {
			throw new TimException(ETimMessages.ALREADY_EXISTS, 
					"Mã Chương Trình Giảng Dạy", education.getCode());
		}
		// Check exists exists By SchoolYear And Faculty 
		boolean check = eduProgramRepository.existsBySchoolYear_CodeAndFaculty_Code(
				education.getSchoolYearCode(), education.getFacultyCode());
		if (check) {
			throw new TimException(ETimMessages.ALREADY_EXISTS,
					EDUCATION_PROGRAM, FACULTY + "(" + education.getFacultyCode() + ") & " 
							+ SCHOOL_YEAR + "(" + education.getSchoolYearCode() + ")");
		}
		// Create eduProgram
		EducationProgram eduProgram = eduProgramConverter.toEntity(education);
		setReferenceEntity(eduProgram, education.getSchoolYearCode(), education.getFacultyCode());
		
		// Create educationProgram_Subjects
		Set<EducationProgramSubject> eduProgramSubjects = getEduProgramSubjects(
				eduProgramSubjectDtos, eduProgram);
		
		EducationProgramDto dto = eduProgramConverter.toDto(eduProgramRepository.save(eduProgram));
		eduProgramSubjectRepository.saveAll(eduProgramSubjects);
		return dto;
	}

	@Override
	@Transactional
	public EducationProgramDto update(EducationProgramUpdateDto updateDto, MultipartFile file) {
		// Validate input 
		ValidationUtils.validateObject(updateDto);
		
		Long eduProgramId = updateDto.getId();
		EducationProgram oldEduProgram = eduProgramRepository.findById(eduProgramId)
				.orElseThrow(() -> new TimNotFoundException(EDUCATION_PROGRAM, 
						"ID", eduProgramId.toString()));
		// Check unique code
		if (!oldEduProgram.getCode().equals(updateDto.getCode())) {
			if (eduProgramRepository.existsByCode(updateDto.getCode())) {
				throw new TimException(ETimMessages.ALREADY_EXISTS, 
						"Mã Chương Trình Giảng Dạy", updateDto.getCode());
			}
		}
		// Check exists exists By SchoolYear And Faculty 
		if (!oldEduProgram.getFaculty().getCode().equals(updateDto.getFacultyCode())
				&& !oldEduProgram.getSchoolYear().getCode().equals(updateDto.getSchoolYearCode())) {
			boolean check = eduProgramRepository.existsBySchoolYear_CodeAndFaculty_Code(
					updateDto.getSchoolYearCode(), updateDto.getFacultyCode());
			if (check) {
				throw new TimException(ETimMessages.ALREADY_EXISTS,
						EDUCATION_PROGRAM, FACULTY + "(" + updateDto.getFacultyCode() + ") & " 
								+ SCHOOL_YEAR + "(" + updateDto.getSchoolYearCode() + ")");
			}
		}
		EducationProgram newEduProgram = eduProgramConverter.toEntity(updateDto, oldEduProgram);
		setReferenceEntity(newEduProgram, updateDto.getSchoolYearCode(), updateDto.getFacultyCode());
		
		// Update list Subject of eduProgram(update middle table)
		Set<EducationProgramSubject> eduProgramSubjects = new HashSet<>();
		List<EducationProgramSubjectCreateDto> eduProgramSubjectDtos = new ArrayList<>();
		if (file != null) {
			eduProgramSubjectDtos = excelService.getListObjectFromExcelFile(
					file, EducationProgramSubjectCreateDto.class);
			eduProgramSubjects = getEduProgramSubjects(eduProgramSubjectDtos, newEduProgram);
		}
		EducationProgramDto dto = eduProgramConverter.toDto(eduProgramRepository.save(newEduProgram));
		if (!eduProgramSubjectDtos.isEmpty()) {
			// Delete all old eduProgramSubjects relevant to new update
			eduProgramSubjectRepository.deleteByEducationProgram_Id(eduProgramId);
			eduProgramSubjectRepository.flush();
			eduProgramSubjectRepository.saveAll(eduProgramSubjects);
		}
		return dto;
	}

	@Override
	public EducationProgramDto getOne(String code) {
		EducationProgram eduProgram = eduProgramRepository.findByCodeAndStatusTrue(code)
				.orElseThrow(() -> new TimNotFoundException(
						EDUCATION_PROGRAM, "Mã", code));
		return eduProgramConverter.toDto(eduProgram);
	}

	@Override
	public long toggleStatus(Set<Long> ids) {
		List<EducationProgram> educationPrograms = new ArrayList<>();
		EducationProgram eduProgram;
		for (Long id : ids) {
			eduProgram = eduProgramRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(EDUCATION_PROGRAM, "ID", id.toString()));
			eduProgram.setStatus(!eduProgram.getStatus());
			educationPrograms.add(eduProgram);
		}
		;
		return eduProgramRepository.saveAll(educationPrograms).size();
	}

	@Override
	public EducationProgramResponseDto getSubjectList(String code) {
		EducationProgram educationProgram = eduProgramRepository.getByCode(code)
				.orElseThrow(() -> new TimNotFoundException(
						EDUCATION_PROGRAM, "Mã Chương Trình Giảng Dạy", code));
		List<EducationProgramSubjectResponseDto> eduSubjectDtos = new ArrayList<>();
		EducationProgramSubjectResponseDto dto;
		for (EducationProgramSubject entity : educationProgram.getEducationProgramSubjects()) {
			dto = eduProgramSubjectConverter.toResponseDto(entity);
			eduSubjectDtos.add(dto);
		}
		EducationProgramResponseDto result = eduProgramConverter.toResponseDto(educationProgram);
		int totalCredits = 0;
		
		for (EducationProgramSubjectResponseDto item : eduSubjectDtos) {
			totalCredits += item.getNumberOfCredits();
		}
		eduSubjectDtos.sort(Comparator
				.comparing(
						EducationProgramSubjectResponseDto::getSemester)
				.thenComparing(Comparator.comparing(
						EducationProgramSubjectResponseDto::getNumberOfCredits))
				.thenComparing(Comparator.comparing(
						EducationProgramSubjectResponseDto::getSubjectName)));
		result.setEducationProgramSubjectDtos(eduSubjectDtos);
		result.setTotalCredits(totalCredits);
		return result;
	}

	private void setReferenceEntity(EducationProgram entity, String schoolYearCode, String facultyCode) {
		// set SchoolYear to entity
		SchoolYear schoolYear = schoolYearRepository.findByCodeAndStatusTrue(schoolYearCode)
				.orElseThrow(() -> new TimNotFoundException(SCHOOL_YEAR, "Mã niên khóa", schoolYearCode));
		entity.setSchoolYear(schoolYear);
		
		// set Faculty to entity
		Faculty faculty = facultyRepository.findByCodeAndStatusTrue(facultyCode)
				.orElseThrow(() -> new TimNotFoundException(FACULTY, "Mã khoa", facultyCode));
		entity.setFaculty(faculty);
	}
	
	private Set<EducationProgramSubject> getEduProgramSubjects(
			List<EducationProgramSubjectCreateDto> eduProgramSubjectDtos, 
			EducationProgram eduProgram) {
		Set<String> subjectCodes = new HashSet<>();
		EducationProgramSubject eduProgramSubject;
		Subject subject;
		Set<EducationProgramSubject> eduProgramSubjects = new HashSet<>();
		
		for (EducationProgramSubjectCreateDto dto : eduProgramSubjectDtos) {
			
			// Get Subject
			subject = subjectRepository.findByCodeAndStatusTrue(
					dto.getSubjectCode()).orElse(null);
			if (subject == null) {
				subjectCodes.add(dto.getSubjectCode());
			} 
			if (subjectCodes.isEmpty()) {
				eduProgramSubject = eduProgramSubjectConverter.toEntity(dto);
				eduProgramSubject.setSubject(subject);
				eduProgramSubject.setEducationProgram(eduProgram);
				eduProgramSubjects.add(eduProgramSubject);
			}
		}
		if (subjectCodes.size() > 0) {
			throw new TimException(Arrays.asList(
					MessageUtils.get(ETimMessages.ENTITY_NOT_FOUND, SUBJECT, 
							"Mã Môn Học", subjectCodes.toString())), 
					ETimMessages.INVALID_OBJECT_VALUE);
		}
		return eduProgramSubjects;
	}

	@Override
	public PagingResponseDto getPage(EducationProgramPageRequestDto pageRequestDto) {
		ValidationUtils.validateObject(pageRequestDto);

		TimSpecification<EducationProgram> timSpecification = 
				new TimSpecification<EducationProgram>();

		timSpecification.add(new SearchCriteria(EducationProgram_.STATUS, 
				pageRequestDto.getStatus(), SearchOperation.EQUAL));

		if (StringUtils.isNotBlank(pageRequestDto.getCode())) {
			timSpecification.add(new SearchCriteria(EducationProgram_.CODE, 
					pageRequestDto.getCode(), SearchOperation.EQUAL));
		}
		if (StringUtils.isNotBlank(pageRequestDto.getName())) {
			timSpecification.add(new SearchCriteria(EducationProgram_.NAME, 
					pageRequestDto.getName(), SearchOperation.LIKE));
		}

        Specification<EducationProgram> specification = Specification.where(null);
        if (StringUtils.isNotBlank(pageRequestDto.getFacultyCode())) {
            specification = specification.and((root, query, builder) -> {
                return builder.equal(root.join(EducationProgram_.FACULTY).get(Faculty_.CODE), 
                							pageRequestDto.getFacultyCode());
            });
        }
        if (StringUtils.isNotBlank(pageRequestDto.getSchoolYearCode())) {
			specification = specification.and((root, query, builder) -> {
				return builder.equal(root.join(EducationProgram_.SCHOOL_YEAR).get(SchoolYear_.CODE), 
										pageRequestDto.getSchoolYearCode());
			});
		}
        
        Pageable pageable = PageRequest.of(
        		pageRequestDto.getPage() - 1, 
        		pageRequestDto.getSize(), 
        		Sort.by(EducationProgram_.NAME, EducationProgram_.CREATED_DATE));
        Page<EducationProgram> pageEduPrograms = eduProgramRepository
        									.findAll(specification.and(timSpecification), pageable);
        List<EducationProgramDto> data = eduProgramConverter.toDtoList(pageEduPrograms.getContent());
        
		return new PagingResponseDto(pageEduPrograms.getTotalElements(),
                pageEduPrograms.getTotalPages(),
                pageEduPrograms.getNumber() + 1,
                pageEduPrograms.getSize(),
                data);
	}
}
