package org.project.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseHelper {
    private static SessionFactory sessionFactory;

    private static Session session;

    public static Session startConnection() {

        try {
            sessionFactory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Erro ao configurar o banco de dados.\n" + e);
        }

        return session = sessionFactory.openSession();
    }

//    public List<LineTable> getLines() {
//        lines = session.createQuery("from LineTable l").getResultList();
//        return lines;
//    }
//
//    public List<CategoryTable> getCategories() {
//        categories = session.createQuery("from CategoryTable c").getResultList();
//        return categories;
//    }
//
//    public List<ModelTable> getModels() {
//        models = session.createQuery("from ModelTable m").getResultList();
//        return models;
//    }
//
//    public List<CategoryTable> getCategoriesByLineId(int lineId) {
//        return session.createQuery("from CategoryTable c where c.id_line =:lineId").setParameter("lineId", lineId).getResultList();
//    }
//
//    public List<ModelTable> getModelsByCategoryId(int categoryId) {
//        return session.createQuery("from ModelTable m where m.id_category =:categoryId").setParameter("categoryId",
//                categoryId).getResultList();
//    }

    public void closeConnection() {
        session.close();
        sessionFactory.close();
    }
}
