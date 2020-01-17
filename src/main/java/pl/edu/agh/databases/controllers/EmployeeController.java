package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Employee;
import pl.edu.agh.databases.repositories.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Data
    public static class EmployeeDTO {
        private Integer EmployeeID;
        private String lastName;
        private String firstName;
        private String title;
        private String titleOfCourtesy;
        private Date birthDate;
        private Date hireDate;
        private String address;
        private String city;
        private String region;
        private String postalCode;
        private String country;
        private String homePhone;
        private String extension;
        private String notes;
        private String photoPath;
        private Integer reportsTo;
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}/subordinates")
    public List<EmployeeDTO> getReportsTo(@PathVariable Integer id) {
        return employeeRepository
                .findByID(id)
                .getSubordinates()
                .stream()
                .map(x -> mapper.map(x, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public EmployeeDTO getByID(@PathVariable Integer id) {
        return mapper.map(employeeRepository.findByID(id), EmployeeDTO.class);
    }

    @Transactional
    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        Employee toCreate = mapper.map(employee, Employee.class);

        if (toCreate.getReportsTo() != null) {
            Employee supervisor = employeeRepository.findByID(employee.getReportsTo());
            toCreate.setSupervisor(supervisor);
            supervisor.getSubordinates().add(toCreate);
        }

        Employee created = employeeRepository.save(toCreate);
        return mapper.map(created, EmployeeDTO.class);
    }

    @Transactional
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employee) {
        Employee existing = employeeRepository.findByID(id);
        Employee oldSupervisor = existing.getSupervisor();
        Employee supervisor = employeeRepository.findByID(employee.getReportsTo());
        employee.setEmployeeID(existing.getEmployeeID());
        mapper.map(employee, existing);

        if (employee.getReportsTo() != null && employee.getReportsTo() != oldSupervisor.getEmployeeID()) {
            oldSupervisor.getSubordinates().remove(existing);
            existing.setSupervisor(supervisor);
            supervisor.getSubordinates().add(existing);
        }

        Employee saved = employeeRepository.save(existing);
        return mapper.map(saved, EmployeeDTO.class);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeRepository.findByID(id);
        employee.getSupervisor().getSubordinates().remove(employee);
        employeeRepository.delete(id);
    }
}
