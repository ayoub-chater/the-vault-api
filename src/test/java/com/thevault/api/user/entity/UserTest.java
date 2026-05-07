package com.thevault.api.user.entity;

import com.thevault.api.user.enums.ProfileType;
import com.thevault.api.user.enums.Role;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void shouldHaveCorrectDefaultValues() {
        User user = User.builder()
                .email("ayoub@thevault.com")
                .firstName("Ayoub")
                .lastName("Chater")
                .build();

        assertThat(user.getRole()).isEqualTo(Role.USER);
        assertThat(user.getProfileType()).isEqualTo(ProfileType.NONE);
        assertThat(user.getKeyBalance()).isEqualTo(0);
        assertThat(user.isEmailVerified()).isFalse();
    }

    @Test
    void shouldAllowCustomRole() {
        User admin = User.builder()
                .email("admin@thevault.com")
                .firstName("Admin")
                .lastName("Vault")
                .role(Role.ADMIN)
                .build();

        assertThat(admin.getRole()).isEqualTo(Role.ADMIN);
    }
}
