package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.entities.Category;
import com.dacs.choithuephongtro.mappers.CategoryMapper;
import com.dacs.choithuephongtro.model.CategoryDTO;
import com.dacs.choithuephongtro.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CategoryServiceJPA implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> ListCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categories) {
            categoryDTOList.add(categoryMapper.categoryToCategoryDto(category));
        }

        return categoryDTOList;
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(UUID id) {
        return Optional.ofNullable(categoryMapper.categoryToCategoryDto(categoryRepository
                        .findById(id).orElse(null)));
    }

    @Override
    public CategoryDTO CreateCategory(CategoryDTO categoryDTO) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(categoryMapper.categoryDtoToCategory(categoryDTO)));
    }

    @Override
    public Optional<CategoryDTO> updateCategory(UUID id, CategoryDTO categoryDTO) {
        AtomicReference<Optional<CategoryDTO>> atomicReference = new AtomicReference<>();

        categoryRepository.findById(id).ifPresentOrElse(foundCategory ->{
            foundCategory.setDescription(categoryDTO.getDescription());
            atomicReference.set(Optional.of(categoryMapper.categoryToCategoryDto(categoryRepository.save(foundCategory))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteCategoryById(UUID id) {

        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
