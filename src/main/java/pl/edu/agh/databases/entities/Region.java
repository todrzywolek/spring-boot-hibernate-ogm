package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int regionID;

    private String regionDescription;
}
