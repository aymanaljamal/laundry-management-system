package com.ayman.laundry.security.dto;


import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {


    private String token;


    private String type;


    private Long id;


    private String username;


    private String fullName;


    private String email;


    private String phoneNumber;


    private String profileImage;


    private String role;


    private String status;


    private LocalDateTime lastLogin;


    private Integer failedLoginAttempts;


}