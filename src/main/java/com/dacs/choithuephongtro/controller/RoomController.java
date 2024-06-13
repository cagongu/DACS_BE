package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.Exception.NotFoundException;
import com.dacs.choithuephongtro.model.RoomDTO;
import com.dacs.choithuephongtro.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class RoomController {
    public static final String ROOM_PATH = "/api/v1/room";
    public static final String ROOM_PATH_ID = ROOM_PATH + "/{roomId}";
    @Autowired
    private RoomService roomService;

    @GetMapping(value = ROOM_PATH)
    public Page<RoomDTO> listRooms(@RequestParam(required = false) String roomName,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize) {
        return roomService.listRooms(roomName, pageNumber, pageSize);
    }

    @GetMapping(value = ROOM_PATH+"/by-category")
    public Page<RoomDTO> listRoomsByCategoryId(@RequestParam(required = false) UUID categoryId,
                                               @RequestParam(required = false) Integer pageNumber,
                                               @RequestParam(required = false) Integer pageSize) {
        return roomService.listRoomsByCategoryId(categoryId, pageNumber, pageSize);
    }

    @GetMapping(value = ROOM_PATH_ID)
    public RoomDTO getRoomById(@PathVariable("roomId") UUID roomId) {
        return roomService.getRoomById(roomId).orElseThrow(NotFoundException::new);
    }
}
