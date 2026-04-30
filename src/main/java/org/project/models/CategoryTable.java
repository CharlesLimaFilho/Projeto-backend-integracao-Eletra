package org.project.models;

import org.hibernate.Session;
import org.project.DataBaseHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_category;

    private String category_name;

    private int id_line;

    public CategoryTable(Integer id_category, String category_name){
        this.id_category = id_category;
        this.category_name = category_name;
    }

    public CategoryTable() {
        super();
    }

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getId_line() {
        return id_line;
    }

    public void setId_line(int id_line) {
        this.id_line = id_line;
    }

    public static List<CategoryTable> getCategoriesByLineId(int lineId) {
        Session session = DataBaseHelper.startConnection();

        Query query = session.createQuery("from CategoryTable c where c.id_line = :lineId");
        query.setParameter("lineId", lineId);
        return query.getResultList();
    }
}