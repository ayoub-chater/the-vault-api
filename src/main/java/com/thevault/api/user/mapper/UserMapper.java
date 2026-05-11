package com.thevault.api.user.mapper;

import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.user.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "email", source = "email")
    User toEntity(RegisterRequest request);
}

