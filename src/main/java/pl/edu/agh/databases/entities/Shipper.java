package pl.edu.agh.databases.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Shippers")
public class Shipper {

    @Id
    private int shipperID;

    private String companyName;

    private String phone;
}
