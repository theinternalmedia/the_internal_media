package com.tim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Student;

/**
 * 
 * @appName the_internal_media
 *
 */
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
	Optional<Student> findByUserId(String userId);

	Boolean existsByUserId(String userId);

	List<Student> getByStatusTrue();
	Student getOneById(Long studentId);

	Student findByEmail(String email);

	Student findByName(String name);
}
