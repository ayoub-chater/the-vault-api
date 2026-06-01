package com.thevault.api.quiz.repository;

import com.thevault.api.quiz.entity.StyleSwipeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleSwipeImageRepository extends JpaRepository<StyleSwipeImage, Long> {

    @Query("SELECT s FROM StyleSwipeImage s WHERE s.active = true ORDER BY s.displayOrder ASC")
    List<StyleSwipeImage> findAllActive();
}
