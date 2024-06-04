package com.dacs.choithuephongtro.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "role_id")
    private UUID roleId;

    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "roles")
//    @JsonManagedReference
    private Set<User> users = new HashSet<>();
}
