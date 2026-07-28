package com.ayman.laundry.user.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.user.enums.UserRole;
import com.ayman.laundry.user.enums.UserStatus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_username",
                        columnNames = "username"
                ),
                @UniqueConstraint(
                        name = "uk_user_email",
                        columnNames = "email"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {


    // ===========================
    // Personal Information
    // ===========================


    @Column(nullable = false, length = 50)
    private String firstName;


    @Column(nullable = false, length = 50)
    private String lastName;


    @Column(nullable = false, length = 50)
    private String username;


    @Column(nullable = false, length = 100)
    private String email;


    @Column(nullable = false, length = 255)
    private String password;


    @Column(length = 20)
    private String phoneNumber;


    @Column(length = 500)
    private String profileImage;


    @Column(length = 100)
    private String address;


    @Column(length = 100)
    private String city;



    // ===========================
    // Authorization
    // ===========================


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private UserRole role = UserRole.EMPLOYEE;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;



    // ===========================
    // Account Security
    // ===========================


    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled = true;


    @Column(nullable = false)
    @Builder.Default
    private Boolean accountNonExpired = true;


    @Column(nullable = false)
    @Builder.Default
    private Boolean accountNonLocked = true;


    @Column(nullable = false)
    @Builder.Default
    private Boolean credentialsNonExpired = true;



    @Builder.Default
    private Integer failedLoginAttempts = 0;


    private LocalDateTime lastLogin;


    private LocalDateTime lastFailedLogin;


    private LocalDateTime passwordChangedAt;



    // ===========================
    // Email Verification
    // ===========================


    @Column(nullable = false)
    @Builder.Default
    private Boolean emailVerified = false;


    @Column(length = 100)
    private String verificationToken;



    // ===========================
    // Password Reset
    // ===========================


    @Column(length = 100)
    private String resetPasswordToken;


    private LocalDateTime resetPasswordTokenExpiry;



    // ===========================
    // Helper Methods
    // ===========================


    @Transient
    public String getFullName(){

        return firstName + " " + lastName;

    }


    public void increaseFailedLoginAttempts(){

        if(failedLoginAttempts == null){
            failedLoginAttempts = 0;
        }

        failedLoginAttempts++;

    }


    public void resetFailedLoginAttempts(){

        failedLoginAttempts = 0;

    }

}