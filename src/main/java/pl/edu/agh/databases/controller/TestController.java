package pl.edu.agh.databases.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.databases.hibernate.Hike;
import pl.edu.agh.databases.hibernate.HikeSection;
import pl.edu.agh.databases.hibernate.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> test() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");
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

        return ResponseEntity.ok().build();
    }
}
