package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void roomRegistration(UUID roomId);
    User getUserByUserName(String username) throws UserNotFoundException;
    List<User> getAll() throws UserNotFoundException;
    User getUserById(UUID userId);

    void comfirmRoom(UUID roomId);

    List<User> getUserByRoomId(UUID roomId);
}
