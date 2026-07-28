package com.ayman.laundry.security.service;

import com.ayman.laundry.user.entity.User;
import com.ayman.laundry.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        if (!Boolean.TRUE.equals(user.getAccountNonLocked())) {

            if (user.isLockExpired()) {

                user.unlockAccount();
                userRepository.save(user);

            } else {

                throw new LockedException("Account is locked.");

            }

        }

        return new CustomUserDetails(user);

    }

}