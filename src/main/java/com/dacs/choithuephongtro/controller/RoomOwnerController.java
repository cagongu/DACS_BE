package com.dacs.choithuephongtro.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RoomOwnerController {
    private final String ROOM_OWNER_PATH = "/api/v1/room_owner";
    private final String ROOM_OWNER_PATH_ID = ROOM_OWNER_PATH + "/room_ownerId";

}
