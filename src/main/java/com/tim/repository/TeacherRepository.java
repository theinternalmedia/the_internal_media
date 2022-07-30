package com.tim.repository;

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
}
