package com.tim.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	boolean existsByUserId(String userId);

	List<Student> getByStatusTrue();
	Student getOneById(Long studentId);

	Student findByEmail(String email);

	Student findByName(String name);

	boolean existsByEmail(String userId);

	List<Student> findByUserIdIn(Set<String> userIdSet);

	List<Student> findByEmailIn(Set<String> emailSet);
	
	// Muốn sort by studentName phải tách name 
	// thành lastName, firstName rồi sort by lastName
	List<Student> findAllByOrderByClassz_NameDesc();
}
