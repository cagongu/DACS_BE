package com.dacs.choithuephongtro.repositories;

import com.dacs.choithuephongtro.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String role_name);
}
