package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Regions")
public class Region {

    @Id
    private String regionID;

    private String regionDescription;
}
