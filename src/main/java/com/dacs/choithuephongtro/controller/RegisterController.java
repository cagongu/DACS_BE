package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.repositories.UserRepository;
import com.dacs.choithuephongtro.service.PasswordEncoderImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterController {
    private static final String REGISTER_PATH = "/api/v1/register";

    @Autowired
    private PasswordEncoderImpl passwordEncoder;

    @Autowired
    private UserRepository repository;

    @PostMapping(REGISTER_PATH)
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", REGISTER_PATH + "/" + user.getUserId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
