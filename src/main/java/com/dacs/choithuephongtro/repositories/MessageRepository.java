package com.dacs.choithuephongtro.repositories;

import com.dacs.choithuephongtro.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
}