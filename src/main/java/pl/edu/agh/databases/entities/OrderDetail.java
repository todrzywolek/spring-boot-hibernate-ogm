package pl.edu.agh.databases.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.UUID;

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
