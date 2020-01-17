package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Category;
import pl.edu.agh.databases.entities.Product;
import pl.edu.agh.databases.entities.Supplier;
import pl.edu.agh.databases.repositories.CategoryRepository;
import pl.edu.agh.databases.repositories.ProductRepository;
import pl.edu.agh.databases.repositories.SupplierRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Data
    public static class ProductDTO {
        private Integer productID;
        private Integer categoryID;
        private Integer supplierID;
        private String productName;
        private Boolean discontinued;
        private String quantityPerUnit;
        private BigDecimal unitPrice;
        private Integer unitsInStock;
        private Integer unitsOnOrder;
        private Integer reorderLevel;
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @GetMapping
    public List<ProductDTO> getAll() {
        return productRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public ProductDTO getByID(@PathVariable Integer id) {
        return mapper.map(productRepository.findByID(id), ProductDTO.class);
    }

    @Transactional
    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product toCreate = mapper.map(productDTO, Product.class);
        Category category = categoryRepository.findByID(toCreate.getCategoryID());
        Supplier supplier = supplierRepository.findByID(toCreate.getSupplierID());
        toCreate.setCategory(category);
        toCreate.setSupplier(supplier);
        category.getProducts().add(toCreate);
        supplier.getProducts().add(toCreate);
        productRepository.save(toCreate);
        return mapper.map(toCreate, ProductDTO.class);
    }

    @Transactional
    @PutMapping("{id}")
    public ProductDTO updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        Product toUpdate = productRepository.findByID(id);
        Category oldCategory = toUpdate.getCategory();
        Supplier oldSupplier = toUpdate.getSupplier();

        if (oldCategory.getCategoryID() != productDTO.getCategoryID()) {
            oldCategory.getProducts().remove(toUpdate);
            Category category = categoryRepository.findByID(toUpdate.getCategoryID());
            toUpdate.setCategory(category);
            category.getProducts().add(toUpdate);
        }

        if (oldSupplier.getSupplierID() != productDTO.getSupplierID()) {
            oldSupplier.getProducts().remove(toUpdate);
            Supplier supplier = supplierRepository.findByID(toUpdate.getSupplierID());
            toUpdate.setSupplier(supplier);
            supplier.getProducts().add(toUpdate);
        }

        productDTO.setProductID(toUpdate.getProductID());
        mapper.map(productDTO, toUpdate);
        productRepository.save(toUpdate);
        return mapper.map(toUpdate, ProductDTO.class);
    }

    @Transactional
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Integer id) {
        Product toRemove = productRepository.findByID(id);
        Category oldCategory = toRemove.getCategory();
        Supplier oldSupplier = toRemove.getSupplier();
        oldCategory.getProducts().remove(toRemove);
        oldSupplier.getProducts().remove(toRemove);
        productRepository.delete(id);
    }
}
