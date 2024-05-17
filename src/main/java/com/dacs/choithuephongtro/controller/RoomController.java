package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.Exeption.NotFoundException;
import com.dacs.choithuephongtro.model.RoomDTO;
import com.dacs.choithuephongtro.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class RoomController {
    public static final String ROOM_PATH = "/api/v1/room";
    public static final String ROOM_PATH_ID = ROOM_PATH + "/{roomId}";
    private final RoomService roomService;

    @PatchMapping(ROOM_PATH_ID)
    public ResponseEntity<RoomDTO> updateBeerPatchById(@PathVariable("roomId") UUID roomId, @RequestBody RoomDTO roomDTO) {

        roomService.patchRoomById(roomId, roomDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(ROOM_PATH_ID)
    public ResponseEntity<RoomDTO> deleteById(@PathVariable("roomId") UUID roomId) {

        if (!roomService.deleteRoomById(roomId)) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(ROOM_PATH_ID)
    public ResponseEntity<RoomDTO> updateById(@PathVariable("roomId") UUID roomId, @Validated @RequestBody RoomDTO roomDTO) {

        if (roomService.updateRoomById(roomId, roomDTO).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(ROOM_PATH)
    public ResponseEntity<RoomDTO> handlePost( @RequestBody RoomDTO roomDTO, @RequestParam String CategoryDescription) {

        RoomDTO savedRoom = roomService.saveNewRoom(roomDTO, CategoryDescription);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", ROOM_PATH + "/" + savedRoom.getDetail().getRoom().getRoom_id().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = ROOM_PATH)
    public Page<RoomDTO> listBeers(@RequestParam(required = false) String roomName,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize) {
        return roomService.listRooms(roomName, pageNumber, pageSize);
    }

    @GetMapping(value = ROOM_PATH+"/by-category")
    public Page<RoomDTO> listBeersByCategoryId(@RequestParam(required = false) UUID categoryId,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize) {
        return roomService.listRoomsByCategoryId(categoryId, pageNumber, pageSize);
    }

    @GetMapping(value = ROOM_PATH_ID)
    public RoomDTO getRoomById(@PathVariable("roomId") UUID roomId) {
        return roomService.getRoomById(roomId).orElseThrow(NotFoundException::new);
    }
}
