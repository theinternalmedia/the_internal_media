package com.tim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>, JpaSpecificationExecutor<Faculty>{

	Optional<Faculty> findByCodeAndStatusTrue(String facultyCode);
	
	List<Faculty> findAllByStatus(boolean status);
	
	boolean existsByCode(String code);

}
