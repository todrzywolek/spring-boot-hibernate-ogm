package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Customer;
import pl.edu.agh.databases.repositories.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Data
    public static class CustomerDTO {
        private String customerId;
        private String companyName;
        private String contactName;
        private String contactTitle;
        private String address;
        private String city;
        private String region;
        private String postalCode;
        private String country;
        private String phone;
        private String fax;
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @GetMapping
    public List<CustomerDTO> getAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public CustomerDTO getByID(@PathVariable String id) {
        return mapper.map(customerRepository.findByID(id), CustomerDTO.class);
    }

    @Transactional
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customer) {
        Customer toCreate = mapper.map(customer, Customer.class);
        Customer created = customerRepository.save(toCreate);
        return mapper.map(created, CustomerDTO.class);
    }

    @Transactional
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Customer existingCustomer = customerRepository.findByID(id);
        customer.setCustomerId(existingCustomer.getCustomerId());
        mapper.map(customer, existingCustomer);
        Customer saved = customerRepository.save(existingCustomer);
        return mapper.map(saved, CustomerDTO.class);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerRepository.delete(id);
    }
}
