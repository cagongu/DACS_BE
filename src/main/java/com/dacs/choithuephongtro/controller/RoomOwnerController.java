package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.Exception.NotFoundException;
import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.model.RoomDTO;
import com.dacs.choithuephongtro.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/roomowner")
public class RoomOwnerController {
    private final RoomService roomService;

    @PostMapping("/removeuser")
    public ResponseEntity<Room> removeUserToRoom(@RequestParam UUID roomId, @RequestParam UUID userId ) {
        if(roomService.removeUserToRoom(roomId, userId).isEmpty()){
            throw  new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/adduser")
    public ResponseEntity<RoomDTO> addUserToRoom(@RequestParam UUID roomId, @RequestParam UUID userId) {

        if (roomService.addUserToRoom(roomId, userId).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/patch/{roomId}")
    public ResponseEntity<RoomDTO> updateRoomPatchById(@PathVariable("roomId") UUID roomId, @RequestBody RoomDTO roomDTO) {

        roomService.patchRoomById(roomId, roomDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<RoomDTO> deleteById(@PathVariable("roomId") UUID roomId) {

        if (!roomService.deleteRoomById(roomId)) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomDTO> updateById(@PathVariable("roomId") UUID roomId, @Validated @RequestBody RoomDTO roomDTO) {

        if (roomService.updateRoomById(roomId, roomDTO).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<RoomDTO> handlePost(@RequestBody RoomDTO roomDTO, @RequestParam String CategoryDescription) {

        RoomDTO savedRoom = roomService.saveNewRoom(roomDTO, CategoryDescription);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/roomowner/create" + "/" + savedRoom.getDetail().getRoom().getRoom_id().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


}
