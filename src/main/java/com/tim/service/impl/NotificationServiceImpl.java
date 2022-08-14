package com.tim.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.NotificationConverter;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.entity.Faculty;
import com.tim.entity.Notification;
import com.tim.entity.SchoolYear;
import com.tim.entity.Student;
import com.tim.repository.ClassRepository;
import com.tim.repository.FacultyRepository;
import com.tim.repository.NotificationRepository;
import com.tim.repository.NotificationStudentRepository;
import com.tim.repository.NotificationTeacherRepository;
import com.tim.repository.SchoolYearRepository;
import com.tim.repository.StudentRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{
	
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

	@Override
	public ResponseDto save(NotificationRequestDto requestDto) {
		Notification entity = notificationConverter.toEntity(requestDto);
		entity = notificationRepository.save(entity);
		switch (entity.getType()) {
		case TimConstants.NotificationType.TO_ALL:
			break;
		case TimConstants.NotificationType.TO_STUDENT:
			if(ObjectUtils.isNotEmpty(requestDto.getSchoolYearCodes())) {
				if(ObjectUtils.isNotEmpty(requestDto.getFacultyCodes())) {
//					List<Student> students = studentRepository.getIn
//					notificationStudentRepository
				}
			}
			break;
		default:
			break;
		}
		return null;
	}
	
}
