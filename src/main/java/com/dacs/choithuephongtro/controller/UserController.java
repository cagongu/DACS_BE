package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAll() {
        try{
            return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/add")
//    public ResponseEntity<User> addUser(@RequestBody User user) throws IOException {
//        try{
//            return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
//        }catch (UserAlreadyExistException e){
//            return new ResponseEntity("User already exists", HttpStatus.CONFLICT);
//        }
//    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
        try{
            return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }

}
