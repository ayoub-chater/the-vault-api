package com.thevault.api.user.mapper;

import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.user.dto.AvatarDto;
import com.thevault.api.user.dto.UpdateAvatarRequest;
import com.thevault.api.user.dto.UpdateProfileRequest;
import com.thevault.api.user.dto.UserDto;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.entity.UserAvatar;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "email", source = "email")
    User toEntity(RegisterRequest request);

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateProfileRequest request, @MappingTarget User user);

    AvatarDto toAvatarDto(UserAvatar avatar);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAvatar(UpdateAvatarRequest request, @MappingTarget UserAvatar avatar);
}
