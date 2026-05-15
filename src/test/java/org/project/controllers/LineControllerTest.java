package org.project.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.project.dtos.LineDto;
import org.project.models.LineTable;
import org.project.services.LineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LineControllerTest {

    @Mock
    private LineService lineService;

    @InjectMocks
    @Spy
    private LineController lineController;

//    @Before
//    public void setUp() {
//        lineController = spy(LineController.class);
//    }

    @Test
    public void getAllLinesTest01() {
        List<LineTable> expectedLineTable = new ArrayList<>(Arrays.asList(new LineTable(1, "Cronos"), new LineTable(2,
                "Ares")));

        when(lineService.findAll()).thenReturn(expectedLineTable);

        ResponseEntity<List<LineTable>> response = lineController.getAllLines();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLineTable, response.getBody());
        verify(lineController).getAllLines();
    }

    @Test
    public void getLineByIdTest01() {
        LineTable expectedLineTable = new LineTable(1, "Cronos");

        when(lineService.findById(anyInt())).thenReturn(Optional.of(expectedLineTable));

        ResponseEntity<Object> response = lineController.getLineById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLineTable, response.getBody());
        verify(lineService).findById(1);
    }

    @Test
    public void getLineByIdTest02() {
        LineTable expectedLineTable = new LineTable(2, "Ares");

        when(lineService.findById(anyInt())).thenReturn(Optional.of(expectedLineTable));

        ResponseEntity<Object> response = lineController.getLineById(2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLineTable, response.getBody());
        verify(lineService).findById(2);
    }

    @Test
    public void getLineByIdTest03() {
        when(lineService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = lineController.getLineById(2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Line ID not found", response.getBody());
        verify(lineController).getLineById(2);
    }

    @Test
    public void saveLineTest01() {
        LineTable expectedLineTable = new LineTable(1, "Cronos");
        LineDto savedLineDto = new LineDto();
        savedLineDto.setLineName("Cronos");

        when(lineService.save(any())).thenReturn(expectedLineTable);

        ResponseEntity<Object> response = lineController.saveLine(savedLineDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedLineTable, response.getBody());
    }

    @Test
    public void deleteLineTest01() {
        LineTable expectedLineTable = new LineTable(1, "Cronos");

        when(lineService.findById(anyInt())).thenReturn(Optional.of(expectedLineTable));

        doNothing().when(lineService).delete(any(LineTable.class));

        ResponseEntity<Object> response = lineController.deleteLine(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Line deleted", response.getBody());

        verify(lineService).delete(any(LineTable.class));
    }

    @Test
    public void deleteLineTest02() {
        when(lineService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = lineController.deleteLine(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Line ID not found", response.getBody());
    }

    @Test
    public void postLineTest01() {
        ArgumentCaptor<LineTable> captor = ArgumentCaptor.forClass(LineTable.class);

        LineDto expectedLineDto = new LineDto();
        expectedLineDto.setLineName("Cronos-2");

        LineTable originalLineTable = new LineTable(1, "Cronos");
        LineTable savedLineTable = new LineTable(1, "Cronos-2");

        when(lineService.findById(anyInt())).thenReturn(Optional.of(originalLineTable));

        ResponseEntity<Object> response = lineController.updateLine(1, expectedLineDto);

        verify(lineService).save(captor.capture());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedLineTable.getLine_name(), captor.getValue().getLine_name());

        verify(lineService).save(any(LineTable.class));
    }

    @Test
    public void postLineTest02() {
        LineDto expectedLineDto = new LineDto();
        expectedLineDto.setLineName("Cronos-2");

        when(lineService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = lineController.updateLine(3, expectedLineDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Line ID not found", response.getBody());
    }
}