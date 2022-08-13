package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Teacher;

public interface ClassRepository extends JpaRepository<com.tim.entity.Class, Long> {
	Optional<com.tim.entity.Class> findByClassId(String classId);
	
}
