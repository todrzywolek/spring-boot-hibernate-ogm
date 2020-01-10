package pl.edu.agh.databases.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
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
    private Integer postalCode;
    private String country;
    private String homePhone;
    private Integer extension;
    private String photo;
    private String notes;
    private String photoPath;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ReportsTo")
    private Employee supervisor;

    @Column(name = "ReportsTo", insertable = false, updatable = false)
    private Integer reportsTo;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> subordinates;

    @ManyToMany
    @JoinTable(name = "employeeterritories",
            joinColumns = {
                    @JoinColumn(name = "EmployeeID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "TerritoryID", nullable = false, updatable = false)})
    private List<Territory> territories = new ArrayList<>();
}
