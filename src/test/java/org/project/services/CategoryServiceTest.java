package org.project.services;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.project.models.CategoryTable;
import org.project.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    @Spy
    private CategoryService categoryService;

    private final List<CategoryTable> expectedCategoriesTables = new ArrayList<>(Arrays.asList(
            new CategoryTable(1, "Cronos Old", 1),
            new CategoryTable(2, "Cronos L", 1),
            new CategoryTable(3, "Cronos NG", 1),
            new CategoryTable(4, "Ares TB", 2),
            new CategoryTable(5, "Ares THS", 2)));

    @After
    public void tearDown() {
        categoryRepository = null;
        categoryService = null;
    }

    @Test
    public void findAllTest01() {
        when(categoryRepository.findAll()).thenReturn(expectedCategoriesTables);

        List<CategoryTable> categoriesList = categoryService.findAll();

        for (int i = 0; i < expectedCategoriesTables.size(); i++) {
            assertEquals(expectedCategoriesTables.get(i).getId_category(), categoriesList.get(i).getId_category());
            assertEquals(expectedCategoriesTables.get(i).getCategory_name(), categoriesList.get(i).getCategory_name());
        }

        verify(categoryRepository).findAll();
    }

    @Test
    public void findByIdTest01() {
        CategoryTable expectedCategoryTable = expectedCategoriesTables.get(0);
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(expectedCategoryTable));

        Optional<CategoryTable> categoryTable = categoryService.findById(1);

        assertTrue(categoryTable.isPresent());
        assertEquals(expectedCategoryTable, categoryTable.get());

        verify(categoryRepository).findById(1);
    }

    @Test
    public void findByIdTest02() {
        CategoryTable expectedCategoryTable = expectedCategoriesTables.get(1);
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(expectedCategoryTable));

        Optional<CategoryTable> categoryTable = categoryService.findById(2);

        assertTrue(categoryTable.isPresent());
        assertEquals(expectedCategoryTable, categoryTable.get());

        verify(categoryRepository).findById(2);
    }

    @Test
    public void findByIdTest03() {
        CategoryTable expectedCategoryTable = expectedCategoriesTables.get(2);
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(expectedCategoryTable));

        Optional<CategoryTable> categoryTable = categoryService.findById(3);

        assertTrue(categoryTable.isPresent());
        assertEquals(expectedCategoryTable, categoryTable.get());

        verify(categoryRepository).findById(3);
    }

    @Test
    public void findByIdTest04() {
        CategoryTable expectedCategoryTable = expectedCategoriesTables.get(3);
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(expectedCategoryTable));

        Optional<CategoryTable> categoryTable = categoryService.findById(4);

        assertTrue(categoryTable.isPresent());
        assertEquals(expectedCategoryTable, categoryTable.get());

        verify(categoryRepository).findById(4);
    }

    @Test
    public void findByIdTest05() {
        CategoryTable expectedCategoryTable = expectedCategoriesTables.get(4);
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(expectedCategoryTable));

        Optional<CategoryTable> categoryTable = categoryService.findById(5);

        assertTrue(categoryTable.isPresent());
        assertEquals(expectedCategoryTable, categoryTable.get());

        verify(categoryRepository).findById(5);
    }

    @Test
    public void findByIdTest06() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<CategoryTable> categoryTable = categoryService.findById(6);

        assertFalse(categoryTable.isPresent());

        verify(categoryRepository).findById(6);
    }

    @Test
    public void findCategoriesByLineIdTest01() {
        when(categoryRepository.findByLineId(anyInt())).thenReturn(expectedCategoriesTables.subList(0, 2));

        List<CategoryTable> categoriesList = categoryService.findCategoriesByLineId(1);

        assertEquals(expectedCategoriesTables.subList(0, 2), categoriesList);

        verify(categoryRepository).findByLineId(1);
    }

    @Test
    public void findCategoriesByLineIdTest02() {
        when(categoryRepository.findByLineId(anyInt())).thenReturn(expectedCategoriesTables.subList(3, 4));

        List<CategoryTable> categoriesList = categoryService.findCategoriesByLineId(2);

        assertEquals(expectedCategoriesTables.subList(3, 4), categoriesList);

        verify(categoryRepository).findByLineId(2);
    }

    @Test
    public void saveTest01() {
        CategoryTable expectedCategoryTable = expectedCategoriesTables.get(0);

        when(categoryRepository.save(any(CategoryTable.class))).thenReturn(expectedCategoryTable);

        CategoryTable saveCategoryTable = new CategoryTable(1, "Cronos Old" ,1);

        CategoryTable lineTable = categoryService.save(saveCategoryTable);
        assertEquals(expectedCategoryTable.getId_line(), lineTable.getId_line());

        verify(categoryRepository).save(saveCategoryTable);
    }

    @Test
    public void deleteTest01() {
        CategoryTable deleteCategoryTable = new CategoryTable(1, "Cronos Old" ,1);

        doNothing().when(categoryRepository).delete(any(CategoryTable.class));

        categoryService.delete(deleteCategoryTable);

        verify(categoryRepository).delete(deleteCategoryTable);
    }
}