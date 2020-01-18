package pl.edu.agh.databases.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.agh.databases.spring.entities.Employee;

public interface EmployeeSpringRepository extends MongoRepository<Employee, String> {
}
