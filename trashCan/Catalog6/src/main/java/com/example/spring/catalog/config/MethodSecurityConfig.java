package com.example.spring.catalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

//리소스 서버에서 필요
@Configuration
@EnableGlobalMethodSecurity(
        // @PreAuthorize, @PostAuthorize
        prePostEnabled = true,
        // @Secured
        securedEnabled = true,
        // @RolesAllowed
        jsr250Enabled = true
)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
