package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Supplier;
import pl.edu.agh.databases.repositories.SupplierRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Data
    public static class SupplierDTO {
        private Integer supplierID;
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
        private String homePage;
    }

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @GetMapping
    public List<SupplierDTO> getAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, SupplierDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public SupplierDTO getByID(@PathVariable Integer id) {
        return mapper.map(supplierRepository.findByID(id), SupplierDTO.class);
    }

    @Transactional
    @PostMapping
    public SupplierDTO create(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = mapper.map(supplierDTO, Supplier.class);
        Supplier created = supplierRepository.save(supplier);
        return mapper.map(created, SupplierDTO.class);
    }

    @Transactional
    @PutMapping("/{id}")
    public SupplierDTO update(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findByID(id);
        supplierDTO.setSupplierID(supplier.getSupplierID());
        mapper.map(supplier, supplierDTO);
        Supplier saved = supplierRepository.save(supplier);
        return mapper.map(saved, SupplierDTO.class);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        supplierRepository.delete(id);
    }

    @Transactional
    @GetMapping("/{id}/products")
    public List<ProductController.ProductDTO> getProducts(@PathVariable Integer id) {
        return supplierRepository
                .findByID(id)
                .getProducts()
                .stream()
                .map(x -> mapper.map(x, ProductController.ProductDTO.class))
                .collect(Collectors.toList());
    }
}
