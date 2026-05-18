package org.project.services;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.project.models.CategoryTable;
import org.project.models.ModelTable;
import org.project.repositories.ModelRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ModelServiceTest {

    @Mock
    private ModelRepository modelRepository;

    @InjectMocks
    @Spy
    private ModelService modelService;

    @After
    public void tearDown() {
        modelRepository = null;
        modelService = null;
    }

    @Test
    public void findAllTest01() {
        List<ModelTable> expectedModelTables = new ArrayList<>(Arrays.asList(new ModelTable(1, "Cronos 6001-A", 1),
                new ModelTable(2, "Cronos 6003", 1)));

        when(modelRepository.findAll()).thenReturn(expectedModelTables);

        List<ModelTable> modelList = modelService.findAll();

        assertEquals(expectedModelTables, modelList);

        verify(modelRepository).findAll();
    }

    @Test
    public void findByIdTest01() {
        ModelTable expectedModelTable = new ModelTable(1, "Cronos 6001-A", 1);

        when(modelRepository.findById(anyInt())).thenReturn(Optional.of(expectedModelTable));

        Optional<ModelTable> modelTable = modelService.findById(1);

        assertTrue(modelTable.isPresent());
        assertEquals(expectedModelTable, modelTable.get());

        verify(modelRepository).findById(1);
    }

    @Test
    public void findByIdTest02() {
        ModelTable expectedModelTable = new ModelTable(2, "Cronos 6003", 1);

        when(modelRepository.findById(anyInt())).thenReturn(Optional.of(expectedModelTable));

        Optional<ModelTable> modelTable = modelService.findById(2);

        assertTrue(modelTable.isPresent());
        assertEquals(expectedModelTable, modelTable.get());

        verify(modelRepository).findById(2);
    }

    @Test
    public void findByIdTest03() {
        when(modelRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<ModelTable> modelTable = modelService.findById(0);

        assertFalse(modelTable.isPresent());

        verify(modelRepository).findById(0);
    }

    @Test
    public void findModelsByCategoryIdTest01() {
        List<ModelTable> expectedModelTables = new ArrayList<>(Arrays.asList(
                new ModelTable(1, "Cronos 6001-A", 1),
                new ModelTable(2, "Cronos 6003", 1)));

        when(modelRepository.findByCategoryId(anyInt())).thenReturn(expectedModelTables);

        List<ModelTable> modelList = modelService.findModelsByCategoryId(1);

        assertEquals(expectedModelTables, modelList);

        verify(modelRepository).findByCategoryId(1);
    }

    @Test
    public void findModelsByCategoryIdTest02() {
        List<ModelTable> expectedModelTables = new ArrayList<>(Arrays.asList(
            new ModelTable(4, "Cronos 6021L", 2),
            new ModelTable(5, "Cronos 7023L", 2)));

        when(modelRepository.findByCategoryId(anyInt())).thenReturn(expectedModelTables);

        List<ModelTable> modelList = modelService.findModelsByCategoryId(2);

        assertEquals(expectedModelTables, modelList);

        verify(modelRepository).findByCategoryId(2);
    }

    @Test
    public void saveModelTest01() {
        ModelTable expectedModelTable = new ModelTable(1, "Cronos 6001-A", 1);

        when(modelRepository.save(any(ModelTable.class))).thenReturn(expectedModelTable);

        ModelTable savedModelTable = modelService.save(expectedModelTable);

        assertEquals(expectedModelTable, savedModelTable);

        verify(modelRepository).save(expectedModelTable);
    }

    @Test
    public void deleteTest01() {
        ModelTable deleteModelTable = new ModelTable(1, "Cronos 6001-A", 1);

        doNothing().when(modelRepository).delete(deleteModelTable);

        modelService.delete(deleteModelTable);

        verify(modelRepository).delete(deleteModelTable);
    }
}