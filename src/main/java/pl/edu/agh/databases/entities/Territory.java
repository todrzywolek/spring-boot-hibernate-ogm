package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "territories")
@Data
public class Territory {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "TerritoryDescription")
    private String territoryDescription;

    @ManyToOne
    @JoinColumn(name = "RegionID")
    private Region region;

    @ManyToMany(mappedBy = "territories")
    private List<Employee> employees = new ArrayList<>();
}
