package pl.edu.agh.databases.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.agh.databases.spring.entities.Customer;

public interface CustomerSpringRepository extends MongoRepository<Customer, String> {
}
