package pl.edu.agh.databases.entities;

import lombok.Data;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ObjectId id;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private Order order;

    @Column(name = "OrderID", insertable = false, updatable = false)
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "ProductID", insertable = false, updatable = false)
    private Integer productID;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Discount")
    private BigDecimal discount;
}
