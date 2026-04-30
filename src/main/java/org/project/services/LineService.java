package org.project.services;

import org.project.models.LineTable;
import org.project.repositories.LineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LineService {

    final LineRepository lineRepository;

    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @Transactional
    public LineTable save(LineTable lineTable) {
        return lineRepository.save(lineTable);
    }

    public List<LineTable> findAll() {
        return lineRepository.findAll();
    }

    public Optional<LineTable> findById(Integer idLine) {
        return lineRepository.findById(idLine);
    }

    @Transactional
    public void delete(LineTable lineTable) {
        lineRepository.delete(lineTable);
    }
}
