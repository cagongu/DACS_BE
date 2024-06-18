package com.dacs.choithuephongtro.model;

import com.dacs.choithuephongtro.entities.Category;
import com.dacs.choithuephongtro.entities.Detail;
import com.dacs.choithuephongtro.entities.LeaseContract;
import com.dacs.choithuephongtro.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class RoomDTO {
    private UUID room_id;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;

    private String name;
    private Boolean register;
    private Boolean enable;

    private Detail detail;

    private String room_owner_id;
    private Category category;

    @JsonIgnore
    private Set<LeaseContract> contracts;

    @JsonIgnore
    private Set<User> listUsers;
}
