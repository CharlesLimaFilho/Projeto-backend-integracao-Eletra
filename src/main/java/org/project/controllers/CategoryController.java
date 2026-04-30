package org.project.controllers;

import org.project.dtos.CategoryDto;
import org.project.models.CategoryTable;
import org.project.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/category")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryTable categoryTable = new CategoryTable();
        BeanUtils.copyProperties(categoryDto, categoryTable);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryTable));
    }

    @GetMapping
    public ResponseEntity<List<CategoryTable>> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/{id_line}")
    public ResponseEntity<Object> getCategoriesByLineId(@PathVariable(value = "id_line") Integer id_line) {
        List<CategoryTable> categoryTableList = categoryService.findCategoriesByLineId(id_line);

        if (categoryTableList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Line ID not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(categoryTableList);
        }
    }

    @DeleteMapping("/{id_category}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id_category") Integer id_category) {
        Optional<CategoryTable> categoryTableOptional = categoryService.findById(id_category);

        if (!categoryTableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }

        categoryService.delete(categoryTableOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted");
    }

    @PutMapping("/{id_category}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value = "id_category") Integer id_category,
                                                 @RequestBody @Valid CategoryDto categoryDto) {
        Optional<CategoryTable> categoryTableOptional = categoryService.findById(id_category);

        if (!categoryTableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }

        CategoryTable categoryTable = categoryTableOptional.get();

        categoryTable.setCategory_name(categoryDto.getCategory_name());
        categoryTable.setId_line(categoryDto.getId_line());

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(categoryTable));
    }
}
