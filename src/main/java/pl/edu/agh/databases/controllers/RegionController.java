package pl.edu.agh.databases.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private EntityManager entityManager;

    public RegionController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<?> getRegions() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List regions = entityManager
                .createQuery("SELECT r FROM Region r")
                .setMaxResults(3)
                .getResultList();
        transaction.commit();

        return ResponseEntity.ok(regions);
    }
}
