package com.ayman.laundry.security.service;

import com.ayman.laundry.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE.equals(user.getAccountNonExpired());
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE.equals(user.getAccountNonLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE.equals(user.getCredentialsNonExpired());
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.getEnabled());
    }

    public Long getUserId() {
        return user.getId();
    }

    public String getFullName() {
        return user.getFullName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getProfileImage() {
        return user.getProfileImage();
    }

    public String getRole() {
        return user.getRole().name();
    }

}