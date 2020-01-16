package pl.edu.agh.databases.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.agh.databases.spring.entities.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
