package pl.edu.agh.databases.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.entities.Employee;

import java.util.List;

@Repository
public class EmployeeRepository extends BaseRepository<Employee> {

    public List<Employee> findAll() {
        return query("select x from Employee x");
    }

    public Employee findByID(int id) {
        return entityManager.find(Employee.class, id);
    }

    public Employee save(Employee category) {
        entityManager.persist(category);
        return category;
    }

    public void delete(int id) {
        entityManager.remove(findByID(id));
    }
}
