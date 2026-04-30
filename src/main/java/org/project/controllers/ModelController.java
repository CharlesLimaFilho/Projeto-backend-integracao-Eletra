package org.project.controllers;

import org.project.dtos.ModelDto;
import org.project.models.ModelTable;
import org.project.services.ModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",  maxAge = 3600)
@RequestMapping("/model")
public class ModelController {

    final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ModelDto modelDto) {
        ModelTable modelTable = new ModelTable();
        BeanUtils.copyProperties(modelDto, modelTable);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.save(modelTable));
    }

    @GetMapping
    public ResponseEntity<List<ModelTable>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.findAll());
    }

    @GetMapping("/{id_category}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id_category") Integer id_category) {
        List<ModelTable> modelTableList = modelService.findModelsByCategoryId(id_category);

        if (modelTableList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Model ID not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(modelTableList);
    }

    @DeleteMapping("/{id_model}")
    public ResponseEntity<Object> deleteModel(@PathVariable(value = "id_model") Integer id_model) {
        Optional<ModelTable> modelTableOptional = modelService.findById(id_model);

        if (!modelTableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Model ID not found");
        }

        modelService.delete(modelTableOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted model");
    }

    @PutMapping("/{id_model}")
    public ResponseEntity<Object> updateModel(@PathVariable(value = "id_model") Integer id_model,
                                              @RequestBody @Valid ModelDto modelDto) {
        Optional<ModelTable> modelTableOptional = modelService.findById(id_model);

        if (!modelTableOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Model ID not found");
        }

        ModelTable modelTable = new ModelTable();

        modelTable.setModel_name(modelDto.getModel_name());
        modelTable.setId_category(modelDto.getId_category());

        return ResponseEntity.status(HttpStatus.OK).body(modelService.save(modelTable));
    }
}
