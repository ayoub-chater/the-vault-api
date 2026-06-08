package com.thevault.api.brand.mapper;

import com.thevault.api.brand.dto.BrandReferenceDto;
import com.thevault.api.brand.entity.BrandReference;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {

    BrandReferenceDto toDto(BrandReference entity);

    List<BrandReferenceDto> toDtoList(List<BrandReference> entities);
}
