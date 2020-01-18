package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Territory;

import java.util.List;

@Repository
public class TerritoryRepository extends BaseRepository<Territory> {

    public List<Territory> findAll() {
        return query("select x from Territory x");
    }

    public Territory findByID(int id) {
        return entityManager.find(Territory.class, id);
    }

    public Territory save(Territory territory) {
        entityManager.persist(territory);
        return territory;
    }

    public void delete(int id) {
        entityManager.remove(findByID(id));
    }
}
