package com.thevault.api.zebra.repository;

import com.thevault.api.zebra.entity.ZebraSession;
import com.thevault.api.zebra.enums.ZebraSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZebraSessionRepository extends JpaRepository<ZebraSession, Long> {

    Optional<ZebraSession> findFirstByUserIdAndStatusOrderByCreatedAtDesc(Long userId, ZebraSessionStatus status);
}
