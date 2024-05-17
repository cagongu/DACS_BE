package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.model.CategoryDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDTO> ListCategories();

    Optional<CategoryDTO> getCategoryById(UUID id);

    CategoryDTO CreateCategory(CategoryDTO categoryDTO);

    Optional<CategoryDTO> updateCategory(UUID id, CategoryDTO categoryDTO);

    Boolean deleteCategoryById(UUID id);

}
