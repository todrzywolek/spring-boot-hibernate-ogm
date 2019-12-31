package com.example.ogm.hibernate;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class HikeTest {
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        setUpEntityManagerFactory();

        new HikeTest().canPersistAndLoadPersonAndHikes();

        closeEntityManagerFactory();
    }

    //    @BeforeAll
    public static void setUpEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");
    }

    //    @AfterAll
    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    //    @Test
    public void canPersistAndLoadPersonAndHikes() {
        EntityManager entityManager;

        // PART 1 ================================================
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person bob = new Person("Bob", "McRobb");

        Hike cornwall = new Hike(
                "Visiting Land's End", new Date(), 5.5D,
                new HikeSection("Penzance", "Mousehole"),
                new HikeSection("Mousehole", "St. Levan"),
                new HikeSection("St. Levan", "Land's End")
        );

        Hike isleOfWight = new Hike(
                "Exploring Carisbrooke Castle", new Date(), 7.5D,
                new HikeSection("Freshwater", "Calbourne"),
                new HikeSection("Calbourne", "Carisbrooke Castle")
        );

        cornwall.setOrganizer(bob);
        bob.getOrganizedHikes().add(cornwall);

        isleOfWight.setOrganizer(bob);
        bob.getOrganizedHikes().add(isleOfWight);

        entityManager.persist(bob);
        entityManager.getTransaction().commit();
        entityManager.close();

        // PART 2 ================================================
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person loadedPerson = entityManager.find(Person.class, bob.getId());
        assertNotNull(loadedPerson);
        assertEquals("Bob", loadedPerson.getFirstName());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}