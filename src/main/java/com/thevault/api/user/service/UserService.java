package com.thevault.api.user.service;

import com.thevault.api.user.dto.AvatarDto;
import com.thevault.api.user.dto.UpdateAvatarRequest;
import com.thevault.api.user.dto.UpdateProfileRequest;
import com.thevault.api.user.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserDto getCurrentUserProfile();

    UserDto updateProfile(UpdateProfileRequest request);

    UserDto updateProfilePicture(MultipartFile file);

    void deleteProfilePicture();

    AvatarDto getAvatar();

    AvatarDto updateAvatar(UpdateAvatarRequest request);

    void deleteAvatar();
}
