package com.ayman.laundry.security.config;
import com.ayman.laundry.security.jwt.JwtAuthenticationFilter;
import com.ayman.laundry.security.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomUserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {


        http

                // Disable CSRF because we use JWT
                .csrf(csrf -> csrf.disable())


                // JWT authentication is stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                // API Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers(
                                "/api/v1/auth/**"
                        )
                        .permitAll()


                        // All other endpoints require authentication
                        .anyRequest()
                        .authenticated()
                )


                // Authentication Provider
                .authenticationProvider(
                        authenticationProvider()
                )


                // JWT Filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );


        return http.build();
    }



    /**
     * Encrypt passwords using BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }



    /**
     * Responsible for loading users
     * and checking passwords
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){


        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        userDetailsService
                );


        provider.setPasswordEncoder(
                passwordEncoder()
        );


        return provider;
    }



    /**
     * Used in Login Service
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {


        return configuration.getAuthenticationManager();
    }

}