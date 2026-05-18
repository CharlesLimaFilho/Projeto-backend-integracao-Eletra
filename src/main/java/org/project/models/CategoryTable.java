package org.project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
public class CategoryTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_category;

    private String category_name;

    private int id_line;

    public CategoryTable(Integer id_category, String category_name, int id_line) {
        this.id_category = id_category;
        this.category_name = category_name;
        this.id_line = id_line;
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
}