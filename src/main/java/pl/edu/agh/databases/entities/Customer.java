package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String customerId;

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
}
