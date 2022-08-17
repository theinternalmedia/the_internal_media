package com.tim.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Teacher;

/**
 * 
 * @appName the_internal_media
 *
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
	Optional<Teacher> findByUserId(String userId);

	Boolean existsByUserId(String userId);

	Optional<Teacher> getByUserId(String headOfFacultyUserId);

	List<Teacher> getByFaculty_CodeIn(Collection<String> facultyCodes);

	List<Teacher> getByStatusTrue();

}
