package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Region;

import java.util.List;

@Repository
public class RegionRepository extends BaseRepository<Region> {
    public List<Region> findAll() {
        return query("select x from Region x");
    }

    public Region findByID(int id) {
        return entityManager.find(Region.class, id);
    }

    public Region save(Region region) {
        entityManager.persist(region);
        return region;
    }

    public void delete(int id) {
        entityManager.remove(findByID(id));
    }
}
