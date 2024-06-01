package com.dacs.choithuephongtro.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name="room_id",length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID room_id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String name;

    private Boolean register;
    private Boolean enable;

    @JsonManagedReference
    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Detail detail;

    @ManyToOne
    private Category category;

    @Column(name="roomOwner_id",length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = true)
    private UUID roomOwner;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "room_user",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private Set<User> listUsers = new HashSet<>();

    public void addUser(User user){
        this.listUsers.add(user);
        user.getRooms().add(this);
    }

    public void removeUser(User user){
        this.listUsers.remove(user);
        user.getRooms().remove(user);
    }

}
