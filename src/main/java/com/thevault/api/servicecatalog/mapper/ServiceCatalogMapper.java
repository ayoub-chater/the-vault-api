package com.thevault.api.servicecatalog.mapper;

import com.thevault.api.servicecatalog.dto.ServiceCatalogDto;
import com.thevault.api.servicecatalog.entity.ServiceCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceCatalogMapper {

    ServiceCatalogDto toDto(ServiceCatalog entity);

    List<ServiceCatalogDto> toDtoList(List<ServiceCatalog> entities);
}
