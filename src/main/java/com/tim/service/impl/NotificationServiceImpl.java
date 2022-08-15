package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tim.converter.NotificationConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.entity.Notification;
import com.tim.entity.NotificationStudent;
import com.tim.entity.NotificationTeacher;
import com.tim.entity.Student;
import com.tim.entity.Teacher;
import com.tim.repository.NotificationRepository;
import com.tim.repository.NotificationStudentRepository;
import com.tim.repository.NotificationTeacherRepository;
import com.tim.repository.StudentRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.NotificationService;
import com.tim.service.StudentService;
import com.tim.utils.Utility;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationConverter notificationConverter;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private NotificationTeacherRepository notificationTeacherRepository;
	@Autowired
	private NotificationStudentRepository notificationStudentRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private StudentService studentService;

	@Override
	public ResponseDto insert(NotificationRequestDto requestDto) {
		Notification entity = notificationConverter.toEntity(requestDto);
		final Notification notification = notificationRepository.save(entity);

		switch (notification.getType()) {
		case TimConstants.NotificationType.TO_ALL:
			break;
		case TimConstants.NotificationType.TO_STUDENT:
			List<Student> students = new ArrayList<Student>();
			if (ObjectUtils.isNotEmpty(requestDto.getSchoolYearCodes())
					&& ObjectUtils.isNotEmpty(requestDto.getFacultyCodes())) {
				students = studentService.findByChoolYearAndFacultyAndClass(requestDto.getSchoolYearCodes(),
						requestDto.getFacultyCodes(), null);
			} else if (ObjectUtils.isNotEmpty(requestDto.getSchoolYearCodes())
					&& ObjectUtils.isEmpty(requestDto.getFacultyCodes())) {
				students = studentService.findByChoolYearAndFacultyAndClass(requestDto.getSchoolYearCodes(), null,
						null);
			} else if (ObjectUtils.isEmpty(requestDto.getSchoolYearCodes())
					&& ObjectUtils.isNotEmpty(requestDto.getFacultyCodes())) {
				students = studentService.findByChoolYearAndFacultyAndClass(null, requestDto.getFacultyCodes(), null);
			} else if (ObjectUtils.isEmpty(requestDto.getClassCodes())) {
				students = studentService.findByChoolYearAndFacultyAndClass(null, null, requestDto.getClassCodes());
			} else {
				students = studentRepository.getByStatusTrue();
			}
			students.forEach(item -> {
				NotificationStudent notificationStudent = new NotificationStudent();
				notificationStudent.setStudent(item);
				notificationStudent.setNotification(notification);
				notificationStudentRepository.save(notificationStudent);
			});
			break;
		case TimConstants.NotificationType.TO_TEACHER:
			List<Teacher> teachers = new ArrayList<>();
			if (ObjectUtils.isNotEmpty(requestDto.getFacultyCodes())) {
				teachers = teacherRepository.getByFaculty_CodeIn(requestDto.getFacultyCodes());
			} else {
				teachers = teacherRepository.getByStatusTrue();
			}
			teachers.forEach(item -> {
				NotificationTeacher notificationTeacher = new NotificationTeacher();
				notificationTeacher.setTeacher(item);
				notificationTeacher.setNotification(notification);
				notificationTeacherRepository.save(notificationTeacher);
			});
			break;
		default:
			break;
		}
		return new ResponseDto(notificationConverter.toDto(notification));
	}

	@Override
	public ResponseDto getById(Long notificationId) {
		Notification notification = notificationRepository.findById(notificationId).orElse(null);
		if (notification == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
					TimConstants.ActualEntityName.TEACHER, "Mã Thông báo", String.valueOf(notificationId)));
		}
		return new ResponseDto(notificationConverter.toDto(notification));
	}

	@Override
	public PagingResponseDto getPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
		Page<Notification> pageNotification = notificationRepository.findAll(pageable);
		List<NotificationDto> data = notificationConverter.toDtoList(pageNotification.getContent());
		return new PagingResponseDto(pageNotification.getTotalElements(), pageNotification.getTotalPages(),
				pageNotification.getNumber(), pageNotification.getSize(), data);
	}

}
