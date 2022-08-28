package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{

	Optional<Faculty> getByCode(String facultyCode);

}
