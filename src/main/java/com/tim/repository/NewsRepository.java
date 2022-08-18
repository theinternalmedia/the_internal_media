package com.tim.repository;

import com.tim.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    News findByIdAndStatusTrue(Long id);

    News getOneById(Long id);
}
