package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.databases.entities.ProductReport;
import pl.edu.agh.databases.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Data
    public static class ProductSalesQueryDTO {
        private Integer productID;
        private Date from;
        private Date to;
    }

    @Data
    public static class ProductSalesReportDTO {
        private Integer productID;
        private Date from;
        private Date to;
        private Integer count;
    }

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @GetMapping("/productReport")
    public List<ProductReport> getProductSalesReport() {
        return productRepository.generateReport();
    }
}
