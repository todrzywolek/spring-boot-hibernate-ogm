package pl.edu.agh.databases.entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Suppliers")
public class Supplier {
    private static final long serialVersionUID = 1L;

    @Id
    private int supplierID;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();
}
