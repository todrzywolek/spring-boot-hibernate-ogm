package pl.edu.agh.databases.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeControllers {

    private EntityManager entityManager;

    public EmployeeControllers(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List employees = entityManager
                .createQuery("SELECT e FROM Employee e")
                .setMaxResults(3)
                .getResultList();
        transaction.commit();

        return ResponseEntity.ok(employees);
    }
}
