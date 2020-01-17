package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Shippers")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int shipperID;

    private String companyName;

    private String phone;
}
