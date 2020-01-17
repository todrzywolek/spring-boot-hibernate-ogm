package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Order;

import java.util.List;

@Repository
public class OrderRepository extends BaseRepository<Order> {

    public List<Order> findAll() {
        return query("select x from Order x");
    }

    public Order findByID(int id) {
        return entityManager.find(Order.class, id);
    }

    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }

    public void delete(int id) {
        entityManager.remove(findByID(id));
    }
}
