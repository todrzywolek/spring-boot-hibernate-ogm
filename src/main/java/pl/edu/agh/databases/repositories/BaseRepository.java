package pl.edu.agh.databases.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected List<T> query(String query) {
        return entityManager.createQuery(query).getResultList();
    }
}
