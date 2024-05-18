package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("user Not found "));
            return new UserDetailsService(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}