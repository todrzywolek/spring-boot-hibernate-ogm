package pl.edu.agh.databases.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int categoryID;

    private String categoryName;

    private String description;

    @JsonIgnore
    @ToString.Exclude
    private byte[] picture;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}

