package com.ayman.laundry.security.controller;

import com.ayman.laundry.security.dto.LoginRequest;
import com.ayman.laundry.security.dto.LoginResponse;
import com.ayman.laundry.security.jwt.JwtService;
import com.ayman.laundry.security.service.CustomUserDetails;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthGraphQLController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @MutationMapping
public LoginResponse login(
        @Argument("input") @Valid LoginRequest input
) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        authenticationToken
                );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        String jwtToken =
                jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .token(jwtToken)
                .type("Bearer")
                .id(userDetails.getUserId())
                .username(userDetails.getUsername())
                .fullName(userDetails.getFullName())
                .email(userDetails.getEmail())
                .profileImage(userDetails.getProfileImage())
                .role(userDetails.getRole())
                .build();
    }
}