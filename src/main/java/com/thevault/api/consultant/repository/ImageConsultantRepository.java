package com.thevault.api.consultant.repository;

import com.thevault.api.consultant.entity.ImageConsultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageConsultantRepository extends JpaRepository<ImageConsultant, Long> {
}
