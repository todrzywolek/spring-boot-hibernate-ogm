package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int orderID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ShipVia")
    private Shipper shipper;

    private BigDecimal freight;

    private String shipName;

    private String shipAddress;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "pk.order")
//    private List<OrderDetail> orderDetails = new ArrayList<>();
}
