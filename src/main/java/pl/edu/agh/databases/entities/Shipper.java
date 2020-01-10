package pl.edu.agh.databases.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shippers")
@Data
public class Shipper {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String companyName;
    private String phone;
}
