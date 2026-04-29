package org.project.repositories;

import org.project.models.ModelTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelTable, Integer> {

    @Query("from ModelTable m where m.id_category = ?1")
    List<ModelTable> findByCategoryId(int idCategory);
}
