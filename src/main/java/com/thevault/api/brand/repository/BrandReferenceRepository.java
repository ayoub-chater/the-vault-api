package com.thevault.api.brand.repository;

import com.thevault.api.brand.entity.BrandReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandReferenceRepository extends JpaRepository<BrandReference, Long> {

    List<BrandReference> findAllByIsActiveTrueOrderByDisplayOrderAsc();
}
