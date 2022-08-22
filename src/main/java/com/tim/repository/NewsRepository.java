package com.tim.repository;

import com.tim.entity.News;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {
    News findByIdAndStatusTrue(Long id);

    News getOneById(Long id);

	Optional<News> findBySlug(String slug);
}
