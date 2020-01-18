package pl.edu.agh.databases.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int productID;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "CategoryID", nullable = false, insertable = false)
    private Integer categoryID;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    @Column(name = "SupplierID", nullable = false, insertable = false)
    private Integer supplierID;

    private String productName;

    private boolean discontinued;

    private String quantityPerUnit;

    private BigDecimal unitPrice;

    private int unitsInStock;

    private int unitsOnOrder;

    private int reorderLevel;
}
