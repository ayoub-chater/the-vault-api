package com.thevault.api.service_catalog.service;

import com.thevault.api.service_catalog.dto.ServiceCatalogDto;

import java.util.List;

public interface ServiceCatalogService {

    List<ServiceCatalogDto> getAllServices();
}
