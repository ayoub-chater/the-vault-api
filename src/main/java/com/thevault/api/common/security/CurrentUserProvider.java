package com.thevault.api.common.security;

import com.thevault.api.common.exception.UserNotFoundException;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CurrentUserProvider {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException(principal.getUsername()));
    }
}
