package com.dacs.choithuephongtro.repositories;


import com.dacs.choithuephongtro.entities.Category;
import com.dacs.choithuephongtro.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Page<Room> findAllByNameLikeIgnoreCase(String name, Pageable pageable);

    Page<Room> findByCategory(Category category, Pageable pageable);
}
