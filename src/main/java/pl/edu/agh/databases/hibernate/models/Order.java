package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "orderid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @Transient
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "employeeid")
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
    @JoinColumn(name = "shipvia")
    private Shipper shipper;

    @Transient
    private Integer shipperId;

    // TODO: money type
    private float freight;

    @Column(length = 40)
    private String shipName;

    @Column(length = 60)
    private String shipAddress;

    @Column(length = 15)
    private String shipCity;

    @Column(length = 15)
    private String shipRegion;

    @Column(length = 12)
    private String shipPostalCode;

    @Column(length = 15)
    private String shipCountry;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    protected Order() {
    }

    public Order(UUID id, Customer customer, Employee employee, Date orderDate, Date shippedDate, Date requiredDate, Shipper shipper,
                 float freight, String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode, String shipCountry) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.shipper = shipper;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", customerId='" + customerId + '\'' +
                ", employee=" + employee +
                ", employeeId=" + employeeId +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", shippedDate=" + shippedDate +
                ", shipper=" + shipper +
                ", shipperId=" + shipperId +
                ", freight=" + freight +
                ", shipName='" + shipName + '\'' +
                ", shipAddress='" + shipAddress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipRegion='" + shipRegion + '\'' +
                ", shipPostalCode='" + shipPostalCode + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Float.compare(order.freight, freight) != 0) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (customerId != null ? !customerId.equals(order.customerId) : order.customerId != null) return false;
        if (employee != null ? !employee.equals(order.employee) : order.employee != null) return false;
        if (employeeId != null ? !employeeId.equals(order.employeeId) : order.employeeId != null) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        if (requiredDate != null ? !requiredDate.equals(order.requiredDate) : order.requiredDate != null) return false;
        if (shippedDate != null ? !shippedDate.equals(order.shippedDate) : order.shippedDate != null) return false;
        if (shipper != null ? !shipper.equals(order.shipper) : order.shipper != null) return false;
        if (shipperId != null ? !shipperId.equals(order.shipperId) : order.shipperId != null) return false;
        if (shipName != null ? !shipName.equals(order.shipName) : order.shipName != null) return false;
        if (shipAddress != null ? !shipAddress.equals(order.shipAddress) : order.shipAddress != null) return false;
        if (shipCity != null ? !shipCity.equals(order.shipCity) : order.shipCity != null) return false;
        if (shipRegion != null ? !shipRegion.equals(order.shipRegion) : order.shipRegion != null) return false;
        if (shipPostalCode != null ? !shipPostalCode.equals(order.shipPostalCode) : order.shipPostalCode != null)
            return false;
        return shipCountry != null ? shipCountry.equals(order.shipCountry) : order.shipCountry == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (requiredDate != null ? requiredDate.hashCode() : 0);
        result = 31 * result + (shippedDate != null ? shippedDate.hashCode() : 0);
        result = 31 * result + (shipper != null ? shipper.hashCode() : 0);
        result = 31 * result + (shipperId != null ? shipperId.hashCode() : 0);
        result = 31 * result + (freight != +0.0f ? Float.floatToIntBits(freight) : 0);
        result = 31 * result + (shipName != null ? shipName.hashCode() : 0);
        result = 31 * result + (shipAddress != null ? shipAddress.hashCode() : 0);
        result = 31 * result + (shipCity != null ? shipCity.hashCode() : 0);
        result = 31 * result + (shipRegion != null ? shipRegion.hashCode() : 0);
        result = 31 * result + (shipPostalCode != null ? shipPostalCode.hashCode() : 0);
        result = 31 * result + (shipCountry != null ? shipCountry.hashCode() : 0);
        return result;
    }
}
