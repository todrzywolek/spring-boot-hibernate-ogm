package pl.edu.agh.databases.spring.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.spring.entities.Category;
import pl.edu.agh.databases.spring.entities.Customer;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/spring/categories")
public class CategorySpringController {

    private MongoOperations mongoOps;

    public CategorySpringController(@Qualifier("mongoTemplate") MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @GetMapping
    public ResponseEntity<?> getCategories() {
        List<Category> categories = mongoOps.findAll(Category.class);

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category persistedCategory = mongoOps.insert(category);

        return ResponseEntity.created(URI.create("/spring/categories/" + persistedCategory.getCategoryID())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") String id) {
        Category category = mongoOps.findById(id, Category.class);

        return category == null ? ResponseEntity.ok().build() : ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") String id, @RequestBody @Valid Category newCategory) {
        Category category = mongoOps.findById(id, Category.class);

        if (category != null) {
            newCategory.setCategoryID(category.getCategoryID());
            mongoOps.save(newCategory);
            return ResponseEntity.ok().build();
        }
        return addCategory(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCustomer(@PathVariable("id") String id) {
        Category category = mongoOps.findById(id, Category.class);
        if (category != null) {
            mongoOps.remove(category);
        }
        return ResponseEntity.ok().build();
    }
}
