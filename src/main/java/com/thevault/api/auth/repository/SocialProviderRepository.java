package com.thevault.api.auth.repository;

import com.thevault.api.auth.entity.SocialProvider;
import com.thevault.api.auth.enums.SocialProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialProviderRepository extends JpaRepository<SocialProvider, Long> {

    Optional<SocialProvider> findByProviderAndProviderId(SocialProviderType provider, String providerId);
}
