package org.project.services;

import org.project.models.CategoryTable;
import org.project.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryTable save(CategoryTable categoryTable) {
        return categoryRepository.save(categoryTable);
    }

    public List<CategoryTable> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryTable> findById(Integer idCategory) {
        return categoryRepository.findById(idCategory);
    }

    public List<CategoryTable> findCategoriesByLineId(Integer idLine) {
        return categoryRepository.findByLineId(idLine);
    }

    @Transactional
    public void delete(CategoryTable categoryTable) {
        categoryRepository.delete(categoryTable);
    }
}
