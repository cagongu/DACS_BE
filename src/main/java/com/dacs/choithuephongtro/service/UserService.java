package com.dacs.choithuephongtro.service;

import java.util.UUID;

public interface UserService {
    //for room owner
    void addCustomerToRoom(UUID customerId, UUID roomId);
    void removeCustomerToRoom(UUID customerId, UUID roomId);
    void roomRegistration(UUID roomId);

    void comfirmRoom(UUID roomId);
}
