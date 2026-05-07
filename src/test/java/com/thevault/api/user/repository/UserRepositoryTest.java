package com.thevault.api.user.repository;

import com.thevault.api.user.entity.User;
import com.thevault.api.user.enums.Role;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AllArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserByEmail() {
        User user = User.builder()
                .email("ayoub@thevault.com")
                .firstName("Ayoub")
                .lastName("Chater")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("ayoub@thevault.com");

        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("Ayoub");
    }

    @Test
    void shouldReturnTrueWhenEmailExists() {
        User user = User.builder()
                .email("exists@thevault.com")
                .firstName("Test")
                .lastName("User")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        assertThat(userRepository.existsByEmail("exists@thevault.com")).isTrue();
        assertThat(userRepository.existsByEmail("notfound@thevault.com")).isFalse();
    }
}
