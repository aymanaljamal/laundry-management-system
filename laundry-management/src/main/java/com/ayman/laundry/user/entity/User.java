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

    // ==========================================================
    // Personal Information
    // ==========================================================

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

    // ==========================================================
    // Authorization
    // ==========================================================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private UserRole role = UserRole.EMPLOYEE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    // ==========================================================
    // Account Security
    // ==========================================================

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

    @Column(nullable = false)
    @Builder.Default
    private Integer failedLoginAttempts = 0;

    /**
     * آخر تسجيل دخول ناجح
     */
    private LocalDateTime lastLogin;

    /**
     * آخر محاولة دخول فاشلة
     */
    private LocalDateTime lastFailedLogin;

    /**
     * وقت آخر تغيير لكلمة المرور
     */
    private LocalDateTime passwordChangedAt;

    /**
     * إذا كان الحساب مقفلاً، متى ينتهي القفل
     */
    private LocalDateTime accountLockedUntil;

    // ==========================================================
    // Email Verification
    // ==========================================================

    @Column(nullable = false)
    @Builder.Default
    private Boolean emailVerified = false;

    @Column(length = 100)
    private String verificationToken;

    // ==========================================================
    // Password Reset
    // ==========================================================

    @Column(length = 100)
    private String resetPasswordToken;

    private LocalDateTime resetPasswordTokenExpiry;

    // ==========================================================
    // Helper Methods
    // ==========================================================

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * استدعها عند نجاح تسجيل الدخول
     */
    public void successfulLogin() {

        this.lastLogin = LocalDateTime.now();
        this.failedLoginAttempts = 0;
        this.lastFailedLogin = null;
        this.accountNonLocked = true;
        this.accountLockedUntil = null;

    }

    /**
     * استدعها عند فشل تسجيل الدخول
     */
    public void increaseFailedLoginAttempts() {

        if (failedLoginAttempts == null) {
            failedLoginAttempts = 0;
        }

        failedLoginAttempts++;
        lastFailedLogin = LocalDateTime.now();

        if (failedLoginAttempts >= 5) {
            accountNonLocked = false;
            accountLockedUntil = LocalDateTime.now().plusMinutes(30);
        }

    }

    /**
     * إعادة تعيين عداد المحاولات
     */
    public void resetFailedLoginAttempts() {

        failedLoginAttempts = 0;
        lastFailedLogin = null;

    }

    /**
     * هل انتهت مدة قفل الحساب؟
     */
    public boolean isLockExpired() {

        return accountLockedUntil != null
                && LocalDateTime.now().isAfter(accountLockedUntil);

    }

    /**
     * فتح الحساب بعد انتهاء مدة القفل
     */
    public void unlockAccount() {

        accountNonLocked = true;
        failedLoginAttempts = 0;
        accountLockedUntil = null;

    }

    /**
     * تغيير كلمة المرور
     */
    public void passwordChanged() {

        passwordChangedAt = LocalDateTime.now();

    }

}