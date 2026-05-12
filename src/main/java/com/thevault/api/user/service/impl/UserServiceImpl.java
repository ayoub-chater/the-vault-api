package com.thevault.api.user.service.impl;

import com.thevault.api.common.security.CurrentUserProvider;
import com.thevault.api.user.dto.AvatarDto;
import com.thevault.api.user.dto.UpdateAvatarRequest;
import com.thevault.api.user.dto.UpdateProfileRequest;
import com.thevault.api.user.dto.UserDto;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.entity.UserAvatar;
import com.thevault.api.user.enums.ProfileType;
import com.thevault.api.user.mapper.UserMapper;
import com.thevault.api.user.repository.UserAvatarRepository;
import com.thevault.api.user.repository.UserRepository;
import com.thevault.api.user.service.FileStorageService;
import com.thevault.api.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUserProvider currentUserProvider;
    private final UserRepository userRepository;
    private final UserAvatarRepository userAvatarRepository;
    private final FileStorageService fileStorageService;
    private final UserMapper userMapper;

    @Override
    public UserDto getCurrentUserProfile() {
        return userMapper.toDto(currentUserProvider.getCurrentUser());
    }

    @Override
    @Transactional
    public UserDto updateProfile(UpdateProfileRequest request) {
        User user = currentUserProvider.getCurrentUser();
        userMapper.updateEntity(request, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto updateProfilePicture(MultipartFile file) {
        User user = currentUserProvider.getCurrentUser();

        if (user.getProfilePictureUrl() != null) {
            fileStorageService.delete(user.getProfilePictureUrl());
        }

        String url = fileStorageService.store(file, "profile-pictures");
        user.setProfilePictureUrl(url);
        user.setProfileType(ProfileType.PHOTO);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteProfilePicture() {
        User user = currentUserProvider.getCurrentUser();
        fileStorageService.delete(user.getProfilePictureUrl());
        user.setProfilePictureUrl(null);
        user.setProfileType(ProfileType.NONE);
        userRepository.save(user);
    }

    @Override
    public AvatarDto getAvatar() {
        User user = currentUserProvider.getCurrentUser();
        return userAvatarRepository.findByUserId(user.getId())
                .map(userMapper::toAvatarDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public AvatarDto updateAvatar(UpdateAvatarRequest request) {
        User user = currentUserProvider.getCurrentUser();

        UserAvatar avatar = userAvatarRepository.findByUserId(user.getId())
                .orElseGet(() -> UserAvatar.builder().userId(user.getId()).build());

        userMapper.updateAvatar(request, avatar);
        UserAvatar saved = userAvatarRepository.save(avatar);

        user.setProfileType(ProfileType.AVATAR);
        userRepository.save(user);

        return userMapper.toAvatarDto(saved);
    }

    @Override
    @Transactional
    public void deleteAvatar() {
        User user = currentUserProvider.getCurrentUser();
        userAvatarRepository.findByUserId(user.getId())
                .ifPresent(userAvatarRepository::delete);
        user.setProfileType(ProfileType.NONE);
        userRepository.save(user);
    }
}
