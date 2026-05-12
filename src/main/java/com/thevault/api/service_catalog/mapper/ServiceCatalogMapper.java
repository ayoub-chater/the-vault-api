package com.thevault.api.service_catalog.mapper;

import com.thevault.api.service_catalog.dto.ServiceCatalogDto;
import com.thevault.api.service_catalog.entity.ServiceCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceCatalogMapper {

    ServiceCatalogDto toDto(ServiceCatalog entity);

    List<ServiceCatalogDto> toDtoList(List<ServiceCatalog> entities);
}
