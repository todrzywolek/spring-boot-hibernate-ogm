package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Product;
import pl.edu.agh.databases.entities.ProductReport;

import java.util.List;

@Repository
public class ProductRepository extends BaseRepository<Product> {

    public List<Product> findAll() {
        return query("select x from Product x");
    }

    public Product findByID(int id) {
        return entityManager.find(Product.class, id);
    }

    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }

    public void delete(int id) {
        entityManager.remove(findByID(id));
    }

    public List<ProductReport> generateReport() {
        return entityManager
                .createNamedQuery("ProductReport")
                .getResultList();
    }
}
