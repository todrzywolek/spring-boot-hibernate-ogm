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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @Column(name = "CustomerID", insertable = false, updatable = false)
    private String customerID;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name = "EmployeeID", insertable = false, updatable = false)
    private Integer employeeID;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    private Date requiredDate;

    @Temporal(TemporalType.DATE)
    private Date shippedDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ShipVia")
    private Shipper shipper;

    @Column(name = "ShipVia", insertable = false, updatable = false)
    private Integer shipVia;

    private BigDecimal freight;

    private String shipName;

    private String shipAddress;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;
}
