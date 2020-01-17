package pl.edu.agh.databases.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int employeeID;

    private String lastName;

    private String firstName;

    private String title;

    private String titleOfCourtesy;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String homePhone;

    private String extension;

    @ToString.Exclude
    private byte[] photo;

    private String notes;

    private String photoPath;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ReportsTo")
    private Employee supervisor;

    @Column(name = "ReportsTo", insertable = false, updatable = false)
    private Integer reportsTo;

    @ToString.Exclude
    @OneToMany(mappedBy = "supervisor")
    private List<Employee> subordinates;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employeeterritories",
            joinColumns = {
                    @JoinColumn(name = "EmployeeID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "TerritoryID", nullable = false, updatable = false)})
    private List<Territory> territories = new ArrayList<>();
}
