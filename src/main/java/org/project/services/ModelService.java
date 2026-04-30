package org.project.services;

import org.project.models.CategoryTable;
import org.project.models.ModelTable;
import org.project.repositories.ModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Transactional
    public ModelTable save(ModelTable modelTable) {
        return modelRepository.save(modelTable);
    }

    public List<ModelTable> findAll() {
        return modelRepository.findAll();
    }

    public Optional<ModelTable> findById(Integer idModel) {
        return modelRepository.findById(idModel);
    }

    public List<ModelTable> findModelsByCategoryId(Integer idLine) {
        return modelRepository.findByCategoryId(idLine);
    }

    @Transactional
    public void delete(ModelTable modelTable) {
        modelRepository.delete(modelTable);
    }
}
