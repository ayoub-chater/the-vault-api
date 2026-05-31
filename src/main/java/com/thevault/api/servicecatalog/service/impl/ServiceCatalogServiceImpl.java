package com.thevault.api.servicecatalog.service.impl;

import com.thevault.api.servicecatalog.dto.ServiceCatalogDto;
import com.thevault.api.servicecatalog.mapper.ServiceCatalogMapper;
import com.thevault.api.servicecatalog.repository.ServiceCatalogRepository;
import com.thevault.api.servicecatalog.service.ServiceCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceCatalogServiceImpl implements ServiceCatalogService {

    private final ServiceCatalogRepository serviceCatalogRepository;
    private final ServiceCatalogMapper serviceCatalogMapper;

    @Override
    public List<ServiceCatalogDto> getAllServices() {
        return serviceCatalogMapper.toDtoList(
                serviceCatalogRepository.findAllByOrderByDisplayOrderAsc()
        );
    }
}
