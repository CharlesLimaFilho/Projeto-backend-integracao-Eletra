package org.project;

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

    public void closeConnection() {
        session.close();
        sessionFactory.close();
    }
}
