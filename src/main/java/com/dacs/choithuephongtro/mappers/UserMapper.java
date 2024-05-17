package com.dacs.choithuephongtro.mappers;

import com.dacs.choithuephongtro.entities.User;
import com.dacs.choithuephongtro.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserDTO userDTO);
    UserDTO userToUserDto(User user);
}
