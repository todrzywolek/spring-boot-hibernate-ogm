package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Supplier")
public class Supplier {
    private static final long serialVersionUID = 1L;

    @Id
    private String supplierID;

    private String companyName;

    private String contactName;

    private String contactTitle;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String phone;

    private String fax;

    private String homePage;

    @ToString.Exclude
    private List<Product> products;
}
