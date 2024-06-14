package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.repositories.RoomRepository;
import com.dacs.choithuephongtro.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void roomRegistration(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.setRegister(true);
    }

    @Override
    public User getUserByUserName(String username) throws UserNotFoundException {
        Optional<User> user1=userRepository.findByUsername(username);

        if (user1.isPresent()){
            return user1.get();
        }else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUserByFullName(String fullname) throws UserNotFoundException {
        return userRepository.findByFullname(fullname).orElse(null);
    }

    @Override
    public List<User> getAll() throws UserNotFoundException {
        List<User> users=userRepository.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException();
        }else {
            return users;
        }
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void comfirmRoom(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.setEnable(true);
    }

    @Override
    public List<User> getUserByRoomId(UUID roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        List<User> userList = new ArrayList<>();

        if(room.isPresent()){
            if (!room.get().getListUsers().isEmpty()){
                userList = room.get().getListUsers().stream().toList();
            }
        }
        return userList;
    }
}
