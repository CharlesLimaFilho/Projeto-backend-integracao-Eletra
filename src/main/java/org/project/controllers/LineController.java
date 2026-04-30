package org.project.controllers;

import org.project.dtos.LineDto;
import org.project.models.LineTable;
import org.project.services.LineService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/line")
public class LineController {

    final LineService lineService;

    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @PostMapping
    public ResponseEntity<Object> saveLine(@RequestBody @Valid LineDto lineDto) {
        LineTable lineTable = new LineTable();
        BeanUtils.copyProperties(lineDto, lineTable);

        return ResponseEntity.status(HttpStatus.CREATED).body(lineService.save(lineTable));
    }

    @GetMapping
    public ResponseEntity<List<LineTable>> getAllLines() {
        return ResponseEntity.status(HttpStatus.OK).body(lineService.findAll());
    }

    @GetMapping("/{id_line}")
    public ResponseEntity<Object> getLineById(@PathVariable(value = "id_line")  Integer id_line) {
        Optional<LineTable> lineTableOptional = lineService.findById(id_line);

        if (!lineTableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Line ID not found");
        }

        return  ResponseEntity.status(HttpStatus.OK).body(lineTableOptional.get());
    }

    @DeleteMapping("/{id_line}")
    public ResponseEntity<Object> deleteLine(@PathVariable(value = "id_line")  Integer id_line) {
        Optional<LineTable> lineTableOptional = lineService.findById(id_line);

        if (!lineTableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Line ID not found");
        }

        lineService.delete(lineTableOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Line deleted");
    }

    @PutMapping("/{id_line}")
    public ResponseEntity<Object> updateLine(@PathVariable(value = "id_line") Integer id_line,
                                             @RequestBody @Valid LineDto lineDto) {
        Optional<LineTable> lineTableOptional = lineService.findById(id_line);

        if (!lineTableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Line ID not found");
        }

        LineTable lineTable = lineTableOptional.get();

        lineTable.setLine_name(lineDto.getLineName());

        return ResponseEntity.status(HttpStatus.OK).body(lineService.save(lineTable));
    }
}
