package com.ayman.laundry.security.controller;


import com.ayman.laundry.security.dto.LoginRequest;
import com.ayman.laundry.security.dto.LoginResponse;
import com.ayman.laundry.security.jwt.JwtService;
import com.ayman.laundry.security.service.CustomUserDetails;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {



    private final AuthenticationManager authenticationManager;


    private final JwtService jwtService;



    @PostMapping("/login")
    public LoginResponse login(

            @Valid
            @RequestBody LoginRequest request

    ){



        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getUsername(),

                        request.getPassword()

                )

        );



        CustomUserDetails user =

                (CustomUserDetails)
                authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                )
                .getPrincipal();



        String token =
                jwtService.generateToken(user);



        return LoginResponse.builder()

                .token(token)

                .type("Bearer")

                .id(user.getUserId())

                .username(user.getUsername())

                .fullName(user.getFullName())

                .email(user.getEmail())

                .profileImage(user.getProfileImage())

                .role(user.getRole())

                .build();

    }


}