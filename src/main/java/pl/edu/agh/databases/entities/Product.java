package pl.edu.agh.databases.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "CategoryID", insertable = false, updatable = false)
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    /**
     * Storing both foreign key and referenced entity (both target the very same DB column)
     * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
     */
    @Column(name = "SupplierID", insertable = false, updatable = false)
    private Integer supplierId;

    @Column(length = 40)
    private String productName;

    //    FIXME: below doesn't work - doesn't generate table
//    @Column(name = "discontinued", columnDefinition = "INT(1)")
    private Boolean discontinued;

    @Column(length = 20)
    private String quantityPerUnit;

    // TODO: money type
    private float unitPrice;

    private Integer unitsInStock;

    private Integer unitsOnOrder;

    private Integer reorderLevel;
}
