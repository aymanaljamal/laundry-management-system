package com.ayman.laundry.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.user.entity.User;
import com.ayman.laundry.user.enums.UserRole;
import com.ayman.laundry.user.enums.UserStatus;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    // Login
    Optional<User> findByUsername(String username);



    // Email operations
    Optional<User> findByEmail(String email);


    boolean existsByUsername(String username);


    boolean existsByEmail(String email);



    // Find by role
    List<User> findByRole(UserRole role);



    // Find by account status
    List<User> findByStatus(UserStatus status);



    // Email verification
    Optional<User> findByVerificationToken(String token);



    // Password reset
    Optional<User> findByResetPasswordToken(String token);


}