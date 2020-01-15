package pl.edu.agh.databases.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "Products")
public class Product {

    @Id
    private int productID;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "CategoryID", insertable = false, updatable = false)
    private int categoryId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    private String productName;

    private boolean discontinued;

    private String quantityPerUnit;

    private BigDecimal unitPrice;

    private int unitsInStock;

    private int unitsOnOrder;

    private int reorderLevel;
}
