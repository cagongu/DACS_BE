package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.Exception.NotFoundException;
import com.dacs.choithuephongtro.model.CategoryDTO;
import com.dacs.choithuephongtro.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CategoryController {
    private static final String CATEGORY_PATH = "/api/v1/category";
    private static final String CATEGORY_PATH_ID = CATEGORY_PATH + "/{categoryId}";
    private final CategoryService categoryService;

    @GetMapping(CATEGORY_PATH)
    public List<CategoryDTO> listCategories(){
        return categoryService.ListCategories();
    }

    @GetMapping(CATEGORY_PATH_ID)
    public CategoryDTO getCategoryById(@PathVariable("categoryId") UUID categoryId){
        return categoryService.getCategoryById(categoryId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(CATEGORY_PATH)
    public ResponseEntity<CategoryDTO> postCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.CreateCategory(categoryDTO);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location", CATEGORY_PATH + "/" +savedCategory.getCategory_id().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @DeleteMapping(CATEGORY_PATH_ID)
    public ResponseEntity<CategoryDTO> deleteCategoryById(@PathVariable("categoryId") UUID categoryId){
        if(!categoryService.deleteCategoryById(categoryId)){
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CATEGORY_PATH_ID)
    public ResponseEntity<CategoryDTO> updateById(@PathVariable("categoryId") UUID categoryId, @Validated @RequestBody CategoryDTO categoryDTO) {

        if (categoryService.updateCategory(categoryId, categoryDTO).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
