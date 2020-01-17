package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
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
