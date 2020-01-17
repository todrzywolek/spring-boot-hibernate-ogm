package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Supplier;

import java.util.List;

@Repository
public class SupplierRepository extends BaseRepository<Supplier> {
    public List<Supplier> findAll() {
        return query("select x from Supplier x");
    }

    public Supplier findByID(int id) {
        return entityManager.find(Supplier.class, id);
    }

    public Supplier save(Supplier supplier) {
        entityManager.persist(supplier);
        return supplier;
    }

    public void delete(int id) {
        entityManager.remove(findByID(id));
    }
}
