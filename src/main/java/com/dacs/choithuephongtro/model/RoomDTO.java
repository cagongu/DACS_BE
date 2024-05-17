package com.dacs.choithuephongtro.model;

import com.dacs.choithuephongtro.entities.Category;
import com.dacs.choithuephongtro.entities.Detail;
import com.dacs.choithuephongtro.entities.User;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
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
    private Detail detail;

    private Category category;

    private Set<User> listUsers;
}
