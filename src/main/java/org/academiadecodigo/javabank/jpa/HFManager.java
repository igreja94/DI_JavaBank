package org.academiadecodigo.javabank.jpa;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HFManager {

    private static EntityManagerFactory emf;

    private static EntityManagerFactory getFactory(){

        return Persistence.createEntityManagerFactory("unit");

    }

    public static EntityManager getManager(){

        if (emf == null) {

            emf = getFactory();
            return emf.createEntityManager();

        }

        return emf.createEntityManager();

    }

    public static void closeFactory(){

        emf.close();
        System.out.println("Entity Manager Factory closed.");

    }

}