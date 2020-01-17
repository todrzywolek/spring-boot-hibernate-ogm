package pl.edu.agh.databases.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.agh.databases.entities.Order;

public interface OrderSpringRepository extends MongoRepository<Order, String> {

}
