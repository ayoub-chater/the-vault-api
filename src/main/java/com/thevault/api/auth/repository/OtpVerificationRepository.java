package com.thevault.api.auth.repository;

import com.thevault.api.auth.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification> findTopByUserIdAndIsUsedFalseOrderByCreatedAtDesc(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE OtpVerification o SET o.isUsed = true WHERE o.userId = :userId AND o.isUsed = false")
    void invalidateAllByUserId(Long userId);
}
