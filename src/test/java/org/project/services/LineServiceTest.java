package org.project.services;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.project.models.LineTable;
import org.project.repositories.LineRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LineServiceTest {

    @Mock
    private LineRepository lineRepository;

    @InjectMocks
    @Spy
    private LineService lineService;

    @After
    public void tearDown() {
        lineRepository = null;
        lineService = null;
    }

    @Test
    public void findAllTest01() {
        List<LineTable> expectedLineTables = new ArrayList<>(Arrays.asList(new LineTable(1, "Cronos"), new LineTable(2,
                "Ares")));

        when(lineRepository.findAll()).thenReturn(expectedLineTables);

        List<LineTable> linesList = lineService.findAll();

        for (int i = 0; i < expectedLineTables.size(); i++) {
                assertEquals(expectedLineTables.get(i).getId_line(), linesList.get(i).getId_line());
                assertEquals(expectedLineTables.get(i).getLine_name(), linesList.get(i).getLine_name());
        }

        verify(lineRepository).findAll();
    }

    @Test
    public void findByIdTest01() {
        LineTable expectedLineTable = new LineTable(1, "Cronos");

        when(lineRepository.findById(anyInt())).thenReturn(Optional.of(expectedLineTable));

        Optional<LineTable> lineTable = lineService.findById(1);

        assertTrue(lineTable.isPresent());
        assertEquals(expectedLineTable.getId_line(), lineTable.get().getId_line());
        assertEquals(expectedLineTable.getLine_name(), lineTable.get().getLine_name());

        verify(lineRepository).findById(1);
    }

    @Test
    public void findByIdTest02() {
        LineTable expectedLineTable = new LineTable(2, "Ares");

        when(lineRepository.findById(anyInt())).thenReturn(Optional.of(expectedLineTable));

        Optional<LineTable> lineTable = lineService.findById(2);

        assertTrue(lineTable.isPresent());
        assertEquals(expectedLineTable.getId_line(), lineTable.get().getId_line());
        assertEquals(expectedLineTable.getLine_name(), lineTable.get().getLine_name());

        verify(lineRepository).findById(2);
    }

    @Test
    public void findByIdTest03() {
        when(lineRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<LineTable> lineTable = lineService.findById(3);

        assertFalse(lineTable.isPresent());
        verify(lineRepository).findById(3);
    }

    @Test
    public void saveTest01() {
        LineTable expectedLineTable = new LineTable(1, "Cronos");

        when(lineRepository.save(any(LineTable.class))).thenReturn(expectedLineTable);

        LineTable saveLineTable = new LineTable(1, "Cronos");

        LineTable lineTable = lineService.save(saveLineTable);
        assertEquals(expectedLineTable.getId_line(), lineTable.getId_line());

        verify(lineRepository).save(saveLineTable);
    }

    @Test
    public void deleteTest01() {
        LineTable deleteLineTable = new LineTable(1, "Cronos");

        doNothing().when(lineRepository).delete(any(LineTable.class));

        lineService.delete(deleteLineTable);

        verify(lineRepository).delete(deleteLineTable);
    }
}