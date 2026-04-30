package org.project.repositories;

import org.project.models.LineTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<LineTable, Integer> {
}
