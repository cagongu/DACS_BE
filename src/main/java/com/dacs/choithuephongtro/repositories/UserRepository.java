package com.dacs.choithuephongtro.repositories;

import com.dacs.choithuephongtro.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
