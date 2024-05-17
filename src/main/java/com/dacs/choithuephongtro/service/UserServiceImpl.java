package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.mappers.UserMapper;
import com.dacs.choithuephongtro.repositories.RoomRepository;
import com.dacs.choithuephongtro.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public void addCustomerToRoom(UUID customerId, UUID roomId) {
        Optional<User> user = userRepository.findById(customerId);
        Optional<Room> room = roomRepository.findById(roomId);

        if(user.isPresent() && room.isPresent()){
            room.get().addUser(user.get());
            roomRepository.save(room.get());
            userRepository.save(user.get());
        }
    }

    @Override
    public void removeCustomerToRoom(UUID customerId, UUID roomId) {
        Optional<User> user = userRepository.findById(customerId);
        Optional<Room> room = roomRepository.findById(roomId);

        if(user.isPresent() && room.isPresent()){
            room.get().removeUser(user.get());
            roomRepository.save(room.get());
            userRepository.save(user.get());
        }
    }

    @Override
    public void roomRegistration(UUID roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow();

        room.setRegister(true);

    }

    @Override
    public void comfirmRoom(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();

        room.setEnable(true);
    }
}
