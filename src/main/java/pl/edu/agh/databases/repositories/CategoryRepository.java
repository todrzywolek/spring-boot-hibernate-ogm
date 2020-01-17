package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Category;

import java.util.List;

@Repository
public class CategoryRepository extends BaseRepository<Category> {

    public List<Category> findAll() {
        return query("select x from Category x");
    }

    public Category findByID(int id) {
        return entityManager.find(Category.class, id);
    }

    public Category save(Category category) {
        entityManager.persist(category);
        return category;
    }

    public void delete(int id) {
        entityManager.remove(findByID(id));
    }
}
