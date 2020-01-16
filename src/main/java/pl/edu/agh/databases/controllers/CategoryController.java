package pl.edu.agh.databases.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Category;
import pl.edu.agh.databases.repositories.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{ID}")
    public Category getByID(@PathVariable Integer ID) {
        return categoryRepository.findByID(ID);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{ID}")
    public Category updateCategory(@PathVariable Integer ID, @RequestBody Category newCategory) {
        Category category = categoryRepository.findByID(ID);
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setPicture(newCategory.getPicture());
        categoryRepository.save(category);
        return category;
    }
}
