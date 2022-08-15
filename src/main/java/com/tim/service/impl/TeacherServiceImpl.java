package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.TeacherConverter;
import com.tim.data.ETimMessages;
import com.tim.data.SearchOperation;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.dto.teacher.TeacherDto;
import com.tim.entity.Faculty;
import com.tim.entity.Teacher;
import com.tim.repository.FacultyRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.Utility;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private TeacherConverter teacherConverter;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	@Transactional
	public ResponseDto insert(TeacherDto dto) {
		Teacher entity = teacherConverter.toEntity(dto);
		Faculty faculty = facultyRepository.getByCode(dto.getFacultyCode()).orElse(null);
		entity.setFaculty(faculty);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return new ResponseDto(teacherConverter.toDto(teacherRepository.save(entity)));
	}

	@Override
	public ResponseDto findByUserId(String userId) {
		Teacher entity = teacherRepository.findByUserId(userId).orElse(null);
		if (entity == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
					TimConstants.ActualEntityName.TEACHER, "Mã GV", userId));
		}
		return new ResponseDto(teacherConverter.toDto(entity));
	}

	@Override
	@Transactional
	public ResponseDto insert(MultipartFile file) {
		List<TeacherDto> dtoList = excelService.getListObjectFromExcelFile(file, TeacherDto.class);
		List<Teacher> entityList = new ArrayList<Teacher>();
		dtoList.forEach(item -> {
			Teacher entity = teacherConverter.toEntity(item);
			Faculty faculty = facultyRepository.getByCode(item.getFacultyCode()).orElse(null);
			entity.setFaculty(faculty);
			entity.setPassword(encoder.encode(entity.getPassword()));
			entityList.add(entity);
		});
		teacherRepository.saveAll(entityList);
		return new ResponseDto();
	}

	@Override
	public String exportToExcelFile() {
//		excelService.writeListObjectToExcel(TimConstants.ExcelFiledName.TEACHER, teacherDtos);
		return null;
	}

	@Override
	public PagingResponseDto getPaging(String facultyCode, String name, String userId, int page, int size) {
		TimSpecification<Teacher> timSpecification = new TimSpecification<>();
		if (StringUtils.isNotEmpty(name)) {
			timSpecification.add(new SearchCriteria("name", facultyCode, SearchOperation.LIKE));
		}
		if (StringUtils.isNotEmpty(userId)) {
			timSpecification.add(new SearchCriteria("userId", facultyCode, SearchOperation.EQUAL));
		}
		Specification<Teacher> specification = timSpecification;
		if (StringUtils.isNotEmpty(facultyCode)) {
			specification = specification.and((root, query, builder) -> {
				return builder.equal(root.join("faculty").get("code"), facultyCode);
			});
		}
		Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
		Page<Teacher> pageTeachers = teacherRepository.findAll(specification, pageable);
		List<TeacherDto> data = teacherConverter.toDtoList(pageTeachers.getContent());
		return new PagingResponseDto(pageTeachers.getTotalElements(), pageTeachers.getTotalPages(),
				pageTeachers.getNumber(), pageTeachers.getSize(), data);
	}

}
