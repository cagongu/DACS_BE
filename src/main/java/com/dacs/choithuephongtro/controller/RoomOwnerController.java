package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.repositories.UserRepository;
import com.dacs.choithuephongtro.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/roomowner")
public class RoomOwnerController {
    private final UserRepository userRepository;
    private final UserService userService;

    //add new user
    @PutMapping("/add")
    public ResponseEntity<Room> addUserToRoom(@RequestParam UUID customerId, @RequestParam UUID roomId){
        userService.addCustomerToRoom(customerId, roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
