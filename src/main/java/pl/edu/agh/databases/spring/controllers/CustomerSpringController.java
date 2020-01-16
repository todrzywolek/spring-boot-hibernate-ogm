package pl.edu.agh.databases.spring.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.spring.entities.Customer;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/spring/customers")
public class CustomerSpringController {

    private MongoOperations mongoOps;

    public CustomerSpringController(@Qualifier("mongoTemplate") MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        List<Customer> customers = mongoOps.findAll(Customer.class);

        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody @Valid Customer customer) {
        Customer persistedCustomer = mongoOps.insert(customer);

        return ResponseEntity.created(URI.create("/spring/customers/" + persistedCustomer.getCustomerId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") String id) {
        Customer customer = mongoOps.findById(id, Customer.class);

        return customer == null ? ResponseEntity.ok().build() : ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") String id, @RequestBody @Valid Customer newCustomer) {
        Customer customer = mongoOps.findById(id, Customer.class);

        if (customer != null) {
            newCustomer.setCustomerId(customer.getCustomerId());
            mongoOps.save(newCustomer);
            return ResponseEntity.ok().build();
        }
        return addCustomer(newCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCustomer(@PathVariable("id") String id) {
        Customer customer = mongoOps.findById(id, Customer.class);
        if (customer != null) {
            mongoOps.remove(customer);
        }
        return ResponseEntity.ok().build();
    }
}
