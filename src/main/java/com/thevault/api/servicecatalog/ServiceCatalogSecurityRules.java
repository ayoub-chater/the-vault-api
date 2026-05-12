package com.thevault.api.servicecatalog;

import com.thevault.api.common.SecurityRules;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

// Public endpoint — shown in the onboarding carousel before the user is fully authenticated.
@Component
public class ServiceCatalogSecurityRules implements SecurityRules {

    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers("/api/services/**").permitAll();
    }
}
