package com.thevault.api.quiz.repository;

import com.thevault.api.quiz.entity.StyleQuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleQuizQuestionRepository extends JpaRepository<StyleQuizQuestion, Long> {

    List<StyleQuizQuestion> findAllByOrderByDisplayOrderAsc();
}
