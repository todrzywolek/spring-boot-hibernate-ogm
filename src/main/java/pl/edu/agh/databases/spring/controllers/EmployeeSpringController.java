package pl.edu.agh.databases.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.spring.dao.EmployeeSpringRepository;
import pl.edu.agh.databases.spring.entities.Employee;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spring/employees")
public class EmployeeSpringController {

    private EmployeeSpringRepository employeeSpringRepository;

    public EmployeeSpringController(EmployeeSpringRepository employeeSpringRepository) {
        this.employeeSpringRepository = employeeSpringRepository;
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        List<Employee> employees = employeeSpringRepository.findAll();

        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody @Valid Employee employee) {
        Employee persistedEmployee = employeeSpringRepository.save(employee);

        return ResponseEntity.created(URI.create("/spring/employees/" + persistedEmployee.getEmployeeID())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") String id) {
        Optional<Employee> employeeOptional = employeeSpringRepository.findById(id);

        return employeeOptional.isPresent() ? ResponseEntity.ok(employeeOptional.get()) : ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") String id, @RequestBody @Valid Employee newEmployee) {
        Optional<Employee> oldEmployeeOptional = employeeSpringRepository.findById(id);
        if (oldEmployeeOptional.isPresent()) {
            Employee oldEmployee = oldEmployeeOptional.get();
            newEmployee.setEmployeeID(oldEmployee.getEmployeeID());
            employeeSpringRepository.save(newEmployee);
            return ResponseEntity.ok().build();
        }
        return addEmployee(newEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEmployee(@PathVariable("id") String id) {
        Optional<Employee> employeeOptional = employeeSpringRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employeeSpringRepository.delete(employee);
        }
        return ResponseEntity.ok().build();
    }
}
