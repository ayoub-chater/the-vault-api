package com.thevault.api.consultant.mapper;

import com.thevault.api.consultant.dto.ConsultantPortfolioPhotoDto;
import com.thevault.api.consultant.dto.ImageConsultantDto;
import com.thevault.api.consultant.entity.ConsultantPortfolioPhoto;
import com.thevault.api.consultant.entity.ImageConsultant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageConsultantMapper {

    @Mapping(target = "portfolio", ignore = true)
    ImageConsultantDto toDto(ImageConsultant entity);

    ConsultantPortfolioPhotoDto toDto(ConsultantPortfolioPhoto entity);

    List<ConsultantPortfolioPhotoDto> toPortfolioDtoList(List<ConsultantPortfolioPhoto> entities);
}
