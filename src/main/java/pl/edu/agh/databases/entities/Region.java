package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
@Data
public class Region {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "RegionDescription")
    private String regionDescription;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region", fetch = FetchType.LAZY)
    private List<Territory> territories;
}
