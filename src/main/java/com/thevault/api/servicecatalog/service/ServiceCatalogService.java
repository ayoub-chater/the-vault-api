package com.thevault.api.servicecatalog.service;

import com.thevault.api.servicecatalog.dto.ServiceCatalogDto;

import java.util.List;

public interface ServiceCatalogService {

    List<ServiceCatalogDto> getAllServices();
}
