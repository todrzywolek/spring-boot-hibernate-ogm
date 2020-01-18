package pl.edu.agh.databases.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "Employees")
public class Employee {

    @Id
    private String EmployeeID;

    private String lastName;

    private String firstName;

    private String title;

    private String titleOfCourtesy;

    private Date birthDate;

    private Date hireDate;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String homePhone;

    private String extension;

    @ToString.Exclude
    @JsonIgnore
    private byte[] photo;

    private String notes;

    private String photoPath;

    @Field("ReportsTo")
    private int reportsTo;
}
