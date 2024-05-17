package com.dacs.choithuephongtro.model;

import com.dacs.choithuephongtro.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
    private UUID userId;

    private String username;

    private String password;

    private String role;

    private Byte enabled;

    private Set<Room> rooms;
}
