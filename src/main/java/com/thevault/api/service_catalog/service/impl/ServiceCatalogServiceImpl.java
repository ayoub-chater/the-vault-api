package com.thevault.api.service_catalog.service.impl;

import com.thevault.api.service_catalog.dto.ServiceCatalogDto;
import com.thevault.api.service_catalog.mapper.ServiceCatalogMapper;
import com.thevault.api.service_catalog.repository.ServiceCatalogRepository;
import com.thevault.api.service_catalog.service.ServiceCatalogService;
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
