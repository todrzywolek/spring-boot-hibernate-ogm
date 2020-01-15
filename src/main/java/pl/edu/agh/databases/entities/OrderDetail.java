package pl.edu.agh.databases.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"Order Details\"")
public class OrderDetail {

    @Data
    @Embeddable
    public static class OrderDetailPK implements Serializable {
        @ManyToOne(cascade=CascadeType.ALL)
        @JoinColumn(name = "OrderID")
        private Order order;

        @ManyToOne(cascade=CascadeType.ALL)
        @JoinColumn(name = "ProductID")
        private Product product;
    }

    @StaticMetamodel(OrderDetail.class)
    public abstract static class OrderDetailStaticModel {
        public static volatile SingularAttribute<OrderDetail, OrderDetailPK> pk;
        public static volatile SingularAttribute<OrderDetail, BigDecimal> unitPrice;
        public static volatile SingularAttribute<OrderDetail, Integer> quantity;
        public static volatile SingularAttribute<OrderDetail, BigDecimal> discount;
    }

    @StaticMetamodel(OrderDetailPK.class)
    public abstract static class OrderDetailPKStaticModel {
        public static volatile SingularAttribute<OrderDetailPK, Order> order;
        public static volatile SingularAttribute<OrderDetailPK, Product> product;
    }

    @EmbeddedId
    private OrderDetailPK pk;

    private BigDecimal unitPrice;

    private int quantity;

    private BigDecimal discount;
}
