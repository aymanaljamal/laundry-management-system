package com.ayman.laundry.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;

    @Builder.Default
    private String type = "Bearer";

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String profileImage;
    private String role;
}