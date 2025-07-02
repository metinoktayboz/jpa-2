package com.moboz;

import com.moboz.entities.Employee;
import com.moboz.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String puName = "pu-name";
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");     //none, create, update

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), properties);

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            var e1 = new Employee();
            e1.setAddress("Address");
            e1.setName("Employee 2");

            em.persist(e1);
//            em.persist();  -> Adding an entity in the context
//            em.find();     -> Finds by PK, Get from DB and add it to the contest if doesnt already exist
//            em.remove();   -> Marking Entity to removal
//            em.merge();    -> Merges an Entity from outside the context to the context
//            em.refresh();  -> Mirror the context from database
//            em.detach();   -> Taking entity out of the context


            em.getTransaction().commit();   //end of the transaction
        }

    }
}