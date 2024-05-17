package com.dacs.choithuephongtro.repositories;

import com.dacs.choithuephongtro.entities.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetailRepository extends JpaRepository<Detail, UUID> {
}
