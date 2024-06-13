package com.dacs.choithuephongtro.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "user_id")
    private UUID userId;

    private String username;

    private String password;

    private String fullname;

    private String email;

    private Timestamp date_of_birth;

//    cmnd
    private String idNumber;

//    co quan cap
    private String idIssuingAuthority;

//    ngay cap
    private Timestamp idIssueDate;

    private String address;

    private String phoneNumber;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeUser(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    @Builder.Default
    @ManyToMany(mappedBy = "listUsers")
    private Set<Room> rooms = new HashSet<>();
}
