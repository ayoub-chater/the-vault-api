package com.thevault.api.zebra.mapper;

import com.thevault.api.zebra.dto.ZebraSessionDto;
import com.thevault.api.zebra.entity.ZebraSession;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ZebraSessionMapper {

    ZebraSessionDto toDto(ZebraSession entity);
}
