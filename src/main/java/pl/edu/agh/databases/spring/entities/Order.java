package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Orders")
public class Order {

    @Id
    private String orderID;

    private Customer customer;

    private Employee employee;

    @Transient
    private Integer employeeId;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    private Date requiredDate;

    @Temporal(TemporalType.DATE)
    private Date shippedDate;

    private Shipper shipper;

    private BigDecimal freight;

    private String shipName;

    private String shipAddress;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;

    @ToString.Exclude
    private List<OrderDetail> orderDetails;
}
