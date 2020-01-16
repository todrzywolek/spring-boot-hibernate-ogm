package pl.edu.agh.databases.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Category> findAll() {
        return query("select x from Category x");
    }

    public Category findByID(int ID) {
        return entityManager.find(Category.class, ID);
    }

    public Category save(Category category) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        return category;
    }

    private List<Category> query(String query) {
        return entityManager.createQuery(query).getResultList();
    }
}
