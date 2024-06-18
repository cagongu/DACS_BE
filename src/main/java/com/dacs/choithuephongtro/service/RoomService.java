package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.Detail;
import com.dacs.choithuephongtro.model.DetailDTO;
import com.dacs.choithuephongtro.model.RoomDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomService {
    Optional<RoomDTO> getRoomById(UUID id);

    Page<RoomDTO> listRooms(String roomName, Integer pageNumber, Integer pageSize);
    Page<RoomDTO> listRoomsByCategoryId(UUID uuid, Integer pageNumber, Integer pageSize);

    List<RoomDTO> listRoomByIdOwner(UUID uuid);

    RoomDTO saveNewRoom(RoomDTO roomDTO, DetailDTO detailDTO, String CategoryDescription);

    Optional<RoomDTO> updateRoomById(UUID id, RoomDTO room);

    boolean deleteRoomById(UUID id);

    Optional<RoomDTO> patchRoomById(UUID id, RoomDTO room);

    Optional<RoomDTO> addUserToRoom(UUID roomId, UUID userId);

    Optional<RoomDTO> removeUserToRoom(UUID roomId, UUID userId);
}
