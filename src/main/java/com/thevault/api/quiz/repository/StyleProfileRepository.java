package com.thevault.api.quiz.repository;

import com.thevault.api.quiz.entity.StyleProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleProfileRepository extends JpaRepository<StyleProfile, Long> {

    Optional<StyleProfile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

    boolean existsByUserIdAndIsCompletedTrue(Long userId);
}
