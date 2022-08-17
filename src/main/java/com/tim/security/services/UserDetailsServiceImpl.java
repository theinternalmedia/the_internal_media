package com.tim.security.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tim.entity.Role;
import com.tim.entity.Student;
import com.tim.entity.Teacher;
import com.tim.repository.StudentRepository;
import com.tim.repository.TeacherRepository;

/**
 * 
 * @appName the_internal_media
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TeacherRepository teacherRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = new UserModel();
		Student student = studentRepository.findByUserId(username).orElse(null);
		if (student != null) {
			userModel = modelMapper.map(student, UserModel.class);
		} else {
			Teacher teacher = teacherRepository.findByUserId(username)
					.orElseThrow(() -> new UsernameNotFoundException(
							"Uer not found with username: " + username));
			userModel = modelMapper.map(teacher, UserModel.class);
			userModel.setTeacher(true);
		}
		for(Role r : userModel.getRoles()) {
			if(!r.getStatus()) {
				userModel.getRoles().remove(r);
			}
		}
		return UserDetailsImpl.build(userModel);
	}

}
