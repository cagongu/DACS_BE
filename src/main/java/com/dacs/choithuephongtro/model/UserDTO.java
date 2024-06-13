package com.dacs.choithuephongtro.model;

import com.dacs.choithuephongtro.entities.Role;
import com.dacs.choithuephongtro.entities.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
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

    private String fullname;

    private String email;

    private Timestamp date_of_birth;

    private String idNumber;

    private String idIssuingAuthority;

    private Timestamp idIssueDate;

    private String address;

    private String phoneNumber;

    private Set<Room> rooms;

    @JsonIgnore
    private Set<Role> roles;

}
