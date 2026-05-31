package com.thevault.api.servicecatalog.repository;

import com.thevault.api.servicecatalog.entity.ServiceCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCatalogRepository extends JpaRepository<ServiceCatalog, Long> {

    List<ServiceCatalog> findAllByOrderByDisplayOrderAsc();
}
