package com.dacs.choithuephongtro.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name="detail_id",length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID detail_id;

    private Float area;

    private String description;

    @Column(name = "image1", unique = false, nullable = true, length = 100000)
    private byte[] image1;
    @Column(name = "image2", unique = false, nullable = true, length = 100000)
    private byte[] image2;
    @Column(name = "image3", unique = false, nullable = true, length = 100000)
    private byte[] image3;
    @Column(name = "image4", unique = false, nullable = true, length = 100000)
    private byte[] image4;

//    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
