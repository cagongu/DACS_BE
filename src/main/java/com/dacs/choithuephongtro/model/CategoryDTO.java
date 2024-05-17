package com.dacs.choithuephongtro.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private UUID category_id;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String description;
}
