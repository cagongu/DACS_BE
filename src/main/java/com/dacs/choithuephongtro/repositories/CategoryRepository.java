package com.dacs.choithuephongtro.repositories;

import com.dacs.choithuephongtro.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category  findAllByDescriptionIsLikeIgnoreCase(String description);
}
