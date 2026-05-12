package com.thevault.api.user.service;

import com.thevault.api.common.security.CurrentUserProvider;
import com.thevault.api.user.dto.UpdateProfileRequest;
import com.thevault.api.user.dto.UserDto;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.enums.Gender;
import com.thevault.api.user.mapper.UserMapper;
import com.thevault.api.user.repository.UserAvatarRepository;
import com.thevault.api.user.repository.UserRepository;
import com.thevault.api.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private CurrentUserProvider currentUserProvider;
    @Mock private UserRepository userRepository;
    @Mock private UserAvatarRepository userAvatarRepository;
    @Mock private FileStorageService fileStorageService;
    @Mock private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldReturnCurrentUserProfile() {
        User user = User.builder().id(1L).email("ayoub@thevault.com").build();
        UserDto dto = new UserDto();
        dto.setEmail("ayoub@thevault.com");

        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(dto);

        UserDto result = userService.getCurrentUserProfile();

        assertThat(result.getEmail()).isEqualTo("ayoub@thevault.com");
    }

    @Test
    void shouldUpdateProfilePartially() {
        User user = User.builder().id(1L).email("ayoub@thevault.com").build();
        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setFirstName("Ayoub");
        request.setGender(Gender.MALE);

        UserDto dto = new UserDto();
        dto.setFirstName("Ayoub");

        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(dto);

        UserDto result = userService.updateProfile(request);

        verify(userMapper).updateEntity(request, user);
        assertThat(result.getFirstName()).isEqualTo("Ayoub");
    }
}
