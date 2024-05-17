package com.dacs.choithuephongtro.mappers;

import com.dacs.choithuephongtro.entities.Room;
import com.dacs.choithuephongtro.model.RoomDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RoomMapper {
    Room roomDtoToRoom(RoomDTO roomDTO);
    RoomDTO roomToRoomDto(Room room);
}
