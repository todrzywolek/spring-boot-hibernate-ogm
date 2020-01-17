package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Orders")
public class Order {

    @Id
    private String orderID;

    @Field("EmployeeID")
    private Integer employeeId;

    @Field("CustomerID")
    private String customerId;

    private Date orderDate;

    private Date requiredDate;

    private Date shippedDate;

    private BigDecimal freight;

    private String shipName;

    private String shipAddress;

    private String shipCity;

    @Field("ShipRegion")
    private String shipRegion;

    @Field("ShipVia")
    private String shipVia;

    private String shipPostalCode;

    private String shipCountry;

    @ToString.Exclude
    private List<OrderDetail> orderDetails;
}
