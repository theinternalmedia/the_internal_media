package com.tim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.tim.entity.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long>, JpaSpecificationExecutor<Marks> {

}
