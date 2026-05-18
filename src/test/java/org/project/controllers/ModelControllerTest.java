package org.project.controllers;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.project.dtos.ModelDto;
import org.project.models.ModelTable;
import org.project.services.ModelService;
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
public class ModelControllerTest {

    @Mock
    private ModelService modelService;

    @InjectMocks
    private ModelController modelController;

    @After
    public void tearDown() {
        modelService = null;
        modelController = null;
    }


    @Test
    public void getAllModelsTest01() {
        List<ModelTable> expectedModelTable = new ArrayList<>(Arrays.asList(
                new ModelTable(1, "Cronos 6001-A", 1),
                new ModelTable(2, "Cronos 6003", 1)));

        when(modelService.findAll()).thenReturn(expectedModelTable);

        ResponseEntity<List<ModelTable>> response = modelController.getAllModels();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedModelTable, response.getBody());
        verify(modelService).findAll();
    }

    @Test
    public void getModelsByCategoryId01() {
        List<ModelTable> expectedModelTable = new ArrayList<>(Arrays.asList(
                new ModelTable(1, "Cronos Old", 1),
                new ModelTable(2, "Cronos L", 1)));

        when(modelService.findModelsByCategoryId(anyInt())).thenReturn(expectedModelTable);

        ResponseEntity<Object> response = modelController.getModelsByCategoryId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedModelTable, response.getBody());

        verify(modelService).findModelsByCategoryId(1);
    }

    @Test
    public void getModelsByCategoryId02() {
        List<ModelTable> expectedModelTable = new ArrayList<>(Arrays.asList(
                new ModelTable(12, "Ares 7021", 4),
                new ModelTable(13, "Ares 7031", 4)));

        when(modelService.findModelsByCategoryId(anyInt())).thenReturn(expectedModelTable);

        ResponseEntity<Object> response = modelController.getModelsByCategoryId(4);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedModelTable, response.getBody());

        verify(modelService).findModelsByCategoryId(4);
    }

    @Test
    public void getModelsByCategoryId03() {
        List<ModelTable> expectedModelTable = new ArrayList<>();

        when(modelService.findModelsByCategoryId(anyInt())).thenReturn(expectedModelTable);

        ResponseEntity<Object> response = modelController.getModelsByCategoryId(0);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Model ID not found", response.getBody());

        verify(modelService).findModelsByCategoryId(0);
    }

    @Test
    public void saveModelTest01() {
        ArgumentCaptor<ModelTable> captor = ArgumentCaptor.forClass(ModelTable.class);

        ModelTable expectedModelTable = new ModelTable(1, "Cronos 6001-A", 1);
        ModelDto savedModelDto = new ModelDto();
        savedModelDto.setModel_name("Cronos 6001-A");

        when(modelService.save(any(ModelTable.class))).thenReturn(expectedModelTable);

        ResponseEntity<Object> response = modelController.saveModel(savedModelDto);

        verify(modelService).save(captor.capture());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedModelTable.getModel_name(), captor.getValue().getModel_name());
    }

    @Test
    public void deleteModelTest01() {
        ModelTable expectedModelTable = new ModelTable(12, "Ares 7021", 4);

        when(modelService.findById(anyInt())).thenReturn(Optional.of(expectedModelTable));

        doNothing().when(modelService).delete(any(ModelTable.class));

        ResponseEntity<Object> response = modelController.deleteModel(12);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Model deleted", response.getBody());

        verify(modelService).delete(any(ModelTable.class));
    }

    @Test
    public void deleteModelTest02() {
        when(modelService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = modelController.deleteModel(0);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Model ID not found", response.getBody());
    }

    @Test
    public void updateModelTest01() {
        ArgumentCaptor<ModelTable> captor = ArgumentCaptor.forClass(ModelTable.class);

        ModelDto expectedModelDto = new ModelDto();
        expectedModelDto.setModel_name("Cronos 6001-A*");

        ModelTable originalModelTable = new ModelTable(1, "Cronos 6001-A", 1);
        ModelTable savedModelTable = new ModelTable(1, "Cronos 6001-A*", 1);

        when(modelService.findById(anyInt())).thenReturn(Optional.of(originalModelTable));

        ResponseEntity<Object> response = modelController.updateModel(1, expectedModelDto);

        verify(modelService).save(captor.capture());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedModelTable.getModel_name(), captor.getValue().getModel_name());

        verify(modelService).save(any(ModelTable.class));
    }

    @Test
    public void updateModelTest02() {
        ModelDto expectedModelDto = new ModelDto();
        expectedModelDto.setModel_name("Cronos 6001-A*");

        when(modelService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = modelController.updateModel(0, expectedModelDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Model ID not found", response.getBody());
    }

}