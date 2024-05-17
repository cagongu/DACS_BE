package com.dacs.choithuephongtro.mappers;

import com.dacs.choithuephongtro.entities.Category;
import com.dacs.choithuephongtro.model.CategoryDTO;

import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    Category categoryDtoToCategory(CategoryDTO categoryDTO);
    CategoryDTO categoryToCategoryDto(Category category);
}
