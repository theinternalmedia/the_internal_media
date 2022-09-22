package com.tim.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.ClassConverter;
import com.tim.data.ETimMessages;
import com.tim.data.SearchOperation;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassPageRequestDto;
import com.tim.dto.classz.ClassRequestDto;
import com.tim.dto.classz.ClassUpdateRequestDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.entity.Classz;
import com.tim.entity.Faculty;
import com.tim.entity.SchoolYear;
import com.tim.entity.Teacher;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.ClassRepository;
import com.tim.repository.FacultyRepository;
import com.tim.repository.SchoolYearRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.ClassService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.MessageUtils;
import com.tim.utils.ValidationUtils;

@Service
public class ClassServiceImpl implements ClassService {
	
	private static final String CLASSZ = "Lớp Học";
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ClassConverter classConverter;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private SchoolYearRepository schoolYearRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private ExcelService excelService;
	
	
	@Override
	@Transactional
	public ClassDto create(ClassRequestDto requestDto) {
		ValidationUtils.validateObject(requestDto);
		
		if(classRepository.existsByCode(requestDto.getCode())) {
			throw new TimException(ETimMessages.ALREADY_EXISTS, "Mã lớp học",
									requestDto.getCode());
		}
		
		Classz entity = classConverter.toEntity(requestDto);

		// Mapping Faculty
		Faculty faculty = facultyRepository.findByCodeAndStatusTrue(requestDto.getFacultyCode())
				.orElseThrow(() -> new TimNotFoundException(
						"Khoa", "Mã Khoa", requestDto.getFacultyCode()));
		entity.setFaculty(faculty);
		
		// Mapping School Year
		SchoolYear schoolYear = schoolYearRepository.findByCodeAndStatusTrue(
				requestDto.getSchoolYearCode())
				.orElseThrow(() -> new TimException(
						ETimMessages.ENTITY_NOT_FOUND,"Khóa", "Mã Khóa", 
						requestDto.getSchoolYearCode()));
		entity.setSchoolYear(schoolYear);
		
		if(StringUtils.isNotBlank(requestDto.getAdvisorId())) {
			Teacher teacher = teacherRepository.findByUserId(requestDto.getAdvisorId()).orElse(null);
			if(teacher == null) {
				throw new TimNotFoundException("Giảng Viên", "Mã GV", requestDto.getAdvisorId());
			}
			entity.setAdviser(teacher);
		}
		entity = classRepository.save(entity);
		return classConverter.toDto(entity);
	}
	

	@Override
	@Transactional
	public long create(MultipartFile file) {
		List<ClassRequestDto> requestDtos = excelService.
							getListObjectFromExcelFile(file, ClassRequestDto.class);
		
		List<Classz> classes = new ArrayList<>();
		Classz classz;
		Set<String> classCodes = new HashSet<String>();
		for(ClassRequestDto dto: requestDtos) {
			if(classRepository.existsByCode(dto.getCode())) {
				classCodes.add(dto.getCode());
			}
			if(classCodes.isEmpty()) {
				classz = classConverter.toEntity(dto);
				//Mapping reference entity
				setReferenceEntity(dto, classz);
				
				classes.add(classz);
			}
		}
		if(classCodes.size() > 0) {
			throw new TimException(Arrays.asList(
					MessageUtils.get(ETimMessages.ALREADY_EXISTS, "Mã Lớp", 
							classCodes.toString())), 
					ETimMessages.INVALID_OBJECT_VALUE, "Danh Sách Lớp Học");
		}
		classRepository.saveAll(classes);
		return classes.size();
	}


	@Override
	public ClassDto getByCode(String code) {
		Classz classz = classRepository.getByCodeAndStatusTrue(code).orElseThrow(
				() -> new TimNotFoundException(CLASSZ, "Mã lớp học", code));
		return classConverter.toDto(classz);
	}

	@Override
	public PagingResponseDto getPaging(ClassPageRequestDto pageRequestDto) {
		ValidationUtils.validateObject(pageRequestDto);
		
		TimSpecification<Classz> timSpecification = new TimSpecification<Classz>();
		Specification<Classz> specification = Specification.where(null);
		
		timSpecification.add(new SearchCriteria("status", pageRequestDto.getStatus(), 
												SearchOperation.EQUAL));
		if(StringUtils.isNotBlank(pageRequestDto.getCode())) {
			timSpecification.add(new SearchCriteria("code", pageRequestDto.getCode(), 
												SearchOperation.EQUAL));
		}
		if(StringUtils.isNotBlank(pageRequestDto.getName())) {
			timSpecification.add(new SearchCriteria("name", pageRequestDto.getName(), 
												SearchOperation.LIKE));
		}
		if(StringUtils.isNotBlank(pageRequestDto.getFacultyCode())) {
			specification = specification.and((root, query, builder) -> {
				return builder.equal(root.join("faculty").get("code"), 
										pageRequestDto.getFacultyCode());
			});
		}
		if(StringUtils.isNotBlank(pageRequestDto.getSchoolYearCode())) {
			specification = specification.and((root, query, builder) -> {
				return builder.equal(root.join("schoolYear").get("code"), 
										pageRequestDto.getSchoolYearCode());
			});
		}
		Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1,
										pageRequestDto.getSize(), Sort.by("createdDate"));
		specification = specification.and(timSpecification);
		Page<Classz> classPage = classRepository.findAll(specification, pageable);
		List<ClassDto> data = classConverter.toDtoList(classPage.getContent());
		
		return new PagingResponseDto(classPage.getTotalElements(),
									classPage.getTotalPages(),
									classPage.getNumber() + 1,
									classPage.getSize(),
									data);
	}

	@Override
	@Transactional
	public ClassDto update(ClassUpdateRequestDto dto) {
		ValidationUtils.validateObject(dto);
		
		Classz classz = classRepository.findById(dto.getId()).orElseThrow(
				() -> new TimNotFoundException(CLASSZ, "ID", String.valueOf(dto.getId())));
		
		if(!classz.getCode().equals(dto.getCode())) {
			if(classRepository.existsByCode(dto.getCode())) {
				throw new TimException(ETimMessages.ALREADY_EXISTS, 
						"Mã Lớp Học", dto.getCode());
			}
		}
		classz = classConverter.toEntity(dto, classz);
		
		//Mapping reference entity
		setReferenceEntity(dto, classz);
		
		return classConverter.toDto(classRepository.save(classz));
	}
	
	
	@Override
	public long toggleStatus(Set<Long> ids) {
		List<Classz> classzs = new ArrayList<>();
		Classz classz;
		for (Long id : ids) {
			classz = classRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(CLASSZ, "ID", id.toString()));
			classz.setStatus(!classz.getStatus());
			classzs.add(classz);
		}
		classRepository.saveAll(classzs);
		return ids.size();
	}
	
	
	private void setReferenceEntity(ClassRequestDto dto, Classz classz) {
		Faculty faculty = facultyRepository.findByCodeAndStatusTrue(dto.getFacultyCode()).orElseThrow(
				() -> new TimNotFoundException("Khoa", "Mã Khoa", dto.getFacultyCode()));
		classz.setFaculty(faculty);
		
		SchoolYear schoolYear = schoolYearRepository.findByCode(dto.getSchoolYearCode()).orElseThrow(
				() -> new TimNotFoundException("Niên khóa", "Mã Niên khóa", dto.getSchoolYearCode()));
		classz.setSchoolYear(schoolYear);
		
		if(StringUtils.isNotBlank(dto.getAdvisorId())) {
			Teacher teacher = teacherRepository.findByUserId(dto.getAdvisorId()).orElseThrow(
					() -> new TimNotFoundException("Teacher", "Mã GV", dto.getAdvisorId()));
			classz.setAdviser(teacher);			
		}
	}
}