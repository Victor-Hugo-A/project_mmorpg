package com.mmorpg.project_pt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ DESABILITA CSRF CORRETAMENTE
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // ✅ PERMITE TUDO
                );

        return http.build();
    }
}