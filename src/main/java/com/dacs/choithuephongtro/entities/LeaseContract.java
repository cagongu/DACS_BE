package com.dacs.choithuephongtro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "lease_contract")
public class LeaseContract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    //  Bên A
    @ManyToOne
    private User lessor;
    //  Bên B
    @ManyToOne
    private User lessee;

    private String address;

    private BigDecimal RevenuesRent;

    private BigDecimal unitPriceOfElectricity;

    private BigDecimal UnitPriceOfWater;

    private Timestamp contractDuration;

    @Builder.Default
    @ManyToMany(mappedBy = "contracts")
    private Set<Room> rooms = new HashSet<>();

}
