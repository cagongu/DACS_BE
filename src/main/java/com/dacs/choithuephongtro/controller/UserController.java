package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyid/{userId}")
    public User getUserById(@PathVariable("userId") UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/getuserinroombyid/{roomId}")
    public List<User> getUserInRoom(@PathVariable("roomId") UUID roomId) {
        return userService.getUserByRoomId(roomId);
    }


    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
        try {
            return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }
}
