package org.project.models;

import org.hibernate.Session;
import org.project.DataBaseHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "models")
public class ModelTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_model;

    private String model_name;

    private int id_category;

    public ModelTable(int id_model, String model_name, int id_category) {
        this.id_model = id_model;
        this.model_name = model_name;
        this.id_category = id_category;
    }

    public ModelTable() {
        super();
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public static List<ModelTable> getModelsByCategoryId(int categoryId) {
        Session session = DataBaseHelper.startConnection();
        Query query =  session.createQuery("from ModelTable m where m.id_category =:categoryId");

        query.setParameter("categoryId", categoryId).getResultList();
        return query.getResultList();
    }
}