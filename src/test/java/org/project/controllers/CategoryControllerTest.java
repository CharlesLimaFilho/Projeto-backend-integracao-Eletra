package org.project.controllers;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.project.dtos.CategoryDto;
import org.project.models.CategoryTable;
import org.project.models.ModelTable;
import org.project.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @After
    public void tearDown() {
        categoryService = null;
        categoryController = null;
    }


    @Test
    public void getAllCategoriesTest01() {
        List<CategoryTable> expectedCategoryTable = new ArrayList<>(Arrays.asList(
                new CategoryTable(1, "Cronos Old", 1),
                new CategoryTable(2, "Cronos L", 1)));

        when(categoryService.findAll()).thenReturn(expectedCategoryTable);

        ResponseEntity<List<CategoryTable>> response = categoryController.getAllCategory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategoryTable, response.getBody());
        verify(categoryService).findAll();
    }

    @Test
    public void getCategoriesByLineId01() {
        List<CategoryTable> expectedCategoryTable = new ArrayList<>(Arrays.asList(new CategoryTable(1, "Cronos Old",
                1), new CategoryTable(2, "Cronos L", 1)));

        when(categoryService.findCategoriesByLineId(anyInt())).thenReturn(expectedCategoryTable);

        ResponseEntity<Object> response = categoryController.getCategoriesByLineId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategoryTable, response.getBody());

        verify(categoryService).findCategoriesByLineId(1);
    }

    @Test
    public void getCategoriesByLineId02() {
        List<CategoryTable> expectedCategoryTable = new ArrayList<>(Arrays.asList(
                new CategoryTable(4, "Ares TB", 2),
                new CategoryTable(5, "Ares THS", 2)));

        when(categoryService.findCategoriesByLineId(anyInt())).thenReturn(expectedCategoryTable);

        ResponseEntity<Object> response = categoryController.getCategoriesByLineId(2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategoryTable, response.getBody());

        verify(categoryService).findCategoriesByLineId(2);
    }

    @Test
    public void getCategoriesByLineId03() {
        List<CategoryTable> expectedCategoryTable = new ArrayList<>();

        when(categoryService.findCategoriesByLineId(anyInt())).thenReturn(expectedCategoryTable);

        ResponseEntity<Object> response = categoryController.getCategoriesByLineId(3);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Line ID not found", response.getBody());

        verify(categoryService).findCategoriesByLineId(3);
    }

    @Test
    public void saveCategoryTest01() {
        ArgumentCaptor<CategoryTable> captor = ArgumentCaptor.forClass(CategoryTable.class);

        CategoryTable expectedCategoryTable = new CategoryTable(1, "Cronos", 1);
        CategoryDto savedCategoryDto = new CategoryDto();
        savedCategoryDto.setCategory_name("Cronos");

        when(categoryService.save(any(CategoryTable.class))).thenReturn(expectedCategoryTable);

        ResponseEntity<Object> response = categoryController.saveCategory(savedCategoryDto);

        verify(categoryService).save(captor.capture());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedCategoryTable, response.getBody());
    }

    @Test
    public void deleteCategoryTest01() {
        CategoryTable expectedCategoryTable = new CategoryTable(1, "Cronos", 1);

        when(categoryService.findById(anyInt())).thenReturn(Optional.of(expectedCategoryTable));

        doNothing().when(categoryService).delete(any(CategoryTable.class));

        ResponseEntity<Object> response = categoryController.deleteCategory(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category deleted", response.getBody());

        verify(categoryService).delete(any(CategoryTable.class));
    }

    @Test
    public void deleteCategoryTest02() {
        when(categoryService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = categoryController.deleteCategory(0);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Category ID not found", response.getBody());
    }

    @Test
    public void updateCategoryTest01() {
        ArgumentCaptor<CategoryTable> captor = ArgumentCaptor.forClass(CategoryTable.class);

        CategoryDto expectedCategoryDto = new CategoryDto();
        expectedCategoryDto.setCategory_name("Cronos NG+");

        CategoryTable originalCategoryTable = new CategoryTable(3, "Cronos NG", 1);
        CategoryTable savedCategoryTable = new CategoryTable(3, "Cronos NG+", 1);

        when(categoryService.findById(anyInt())).thenReturn(Optional.of(originalCategoryTable));

        ResponseEntity<Object> response = categoryController.updateCategory(1, expectedCategoryDto);

        verify(categoryService).save(captor.capture());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedCategoryTable.getCategory_name(), captor.getValue().getCategory_name());

        verify(categoryService).save(any(CategoryTable.class));
    }

    @Test
    public void updateCategoryTest02() {
        CategoryDto expectedCategoryDto = new CategoryDto();
        expectedCategoryDto.setCategory_name("Cronos-2");

        when(categoryService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = categoryController.updateCategory(3, expectedCategoryDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Category ID not found", response.getBody());
    }
}