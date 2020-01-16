package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "\"Order Details\"")
public class OrderDetail {

    @Id
    private String id;

    private BigDecimal unitPrice;

    private int quantity;

    private BigDecimal discount;
}
