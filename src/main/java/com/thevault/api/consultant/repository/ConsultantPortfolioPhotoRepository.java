package com.thevault.api.consultant.repository;

import com.thevault.api.consultant.entity.ConsultantPortfolioPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantPortfolioPhotoRepository extends JpaRepository<ConsultantPortfolioPhoto, Long> {

    List<ConsultantPortfolioPhoto> findAllByConsultantIdOrderByDisplayOrderAsc(Long consultantId);
}
