package com.dacs.choithuephongtro.Response;

import com.dacs.choithuephongtro.model.DetailDTO;
import com.dacs.choithuephongtro.model.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateRoomResquest {
    private RoomDTO roomDTO;
    private DetailDTO detailDTO;
    private String category;
}
