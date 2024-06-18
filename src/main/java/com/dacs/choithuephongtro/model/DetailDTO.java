package com.dacs.choithuephongtro.model;

import com.dacs.choithuephongtro.entities.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class DetailDTO {
    private UUID detail_id;

    private Float area;

    private String description;

    private byte[] image1;
    private byte[] image2;
    private byte[] image3;
    private byte[] image4;

    @JsonBackReference
    private Room room;
}
