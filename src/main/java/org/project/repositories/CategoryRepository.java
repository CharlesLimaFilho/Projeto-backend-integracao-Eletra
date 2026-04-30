package org.project.repositories;

import org.project.models.CategoryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryTable, Integer> {

    @Query("from CategoryTable c where c.id_line = ?1")
    List<CategoryTable> findByLineId(int idLine);
}
