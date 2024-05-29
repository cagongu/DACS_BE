package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    //for room owner
    void addCustomerToRoom(UUID customerId, UUID roomId);
    void removeCustomerToRoom(UUID customerId, UUID roomId);
    void roomRegistration(UUID roomId);
    User getUserByUserName(String username) throws UserNotFoundException;
    List<User> getAll() throws UserNotFoundException;

    void comfirmRoom(UUID roomId);
}
