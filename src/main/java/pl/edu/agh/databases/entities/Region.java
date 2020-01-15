package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Region")
public class Region {

    @Id
    private int regionID;

    private String regionDescription;
}
