package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Document(collection = "Products")
public class Product {

    @Id
    private String productID;

    private Category category;

    private int categoryId;

    private Supplier supplier;

    private String productName;

    private boolean discontinued;

    private String quantityPerUnit;

    private BigDecimal unitPrice;

    private int unitsInStock;

    private int unitsOnOrder;

    private int reorderLevel;
}
