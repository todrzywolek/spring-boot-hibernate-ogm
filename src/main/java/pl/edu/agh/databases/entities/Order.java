package pl.edu.agh.databases.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class Order {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @Transient
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Transient
    private Integer employeeId;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    private Date requiredDate;

    @Temporal(TemporalType.DATE)
    private Date shippedDate;

    @ManyToOne
    @JoinColumn(name = "ShipVia")
    private Shipper shipper;

    @Transient
    private Integer shipperId;

    private float freight;

    private String shipName;

    private String shipAddress;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
