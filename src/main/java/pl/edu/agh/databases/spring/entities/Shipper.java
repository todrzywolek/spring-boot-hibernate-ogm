package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Shippers")
public class Shipper {

    @Id
    private String shipperID;

    private String companyName;

    private String phone;
}
