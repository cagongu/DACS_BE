package com.dacs.choithuephongtro.repositories;

import com.dacs.choithuephongtro.entities.LeaseContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContractRepository extends JpaRepository<LeaseContract, UUID> {
}