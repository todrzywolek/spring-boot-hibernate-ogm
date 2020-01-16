package pl.edu.agh.databases.spring.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Territories")
public class Territory {

    @Id
    private String territoryID;

    private String territoryDescription;

    private Region region;

    private List<Employee> employees;
}
