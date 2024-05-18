package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.Role;
import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class CustomUserDetails implements UserDetails {
    private UUID userId;

    private String username;

    private String password;

    private String email;

    private Set<Room> rooms;

    private Set<Role> roles;
    public CustomUserDetails(User user) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
