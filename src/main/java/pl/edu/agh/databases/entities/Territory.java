package pl.edu.agh.databases.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Territories")
public class Territory {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int territoryID;

    private String territoryDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RegionID")
    private Region region;

    @Column(name = "RegionID", insertable = false, updatable = false)
    private Integer regionID;

    @ToString.Exclude
    @ManyToMany(mappedBy = "territories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
}
