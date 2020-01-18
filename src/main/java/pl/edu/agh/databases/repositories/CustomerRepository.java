package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Customer;

import java.util.List;

@Repository
public class CustomerRepository extends BaseRepository<Customer> {
    public List<Customer> findAll() {
        return query("select x from Customer x");
    }

    public Customer findByID(String id) {
        return entityManager.find(Customer.class, id);
    }

    public Customer save(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    public void delete(String id) {
        entityManager.remove(findByID(id));
    }
}
