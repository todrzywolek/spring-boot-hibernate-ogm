package pl.edu.agh.databases.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetail {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Order order;

    @Transient
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    @Transient
    private Integer productId;

    // TODO: money type
    private float unitPrice = 0;

    private Integer quantity = 1;

    // TODO: money type (fixed point)
    private float discount = 0;
}
