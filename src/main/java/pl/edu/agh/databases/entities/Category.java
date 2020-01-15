package pl.edu.agh.databases.entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    private int categoryID;

    private String categoryName;

    private String description;

    @ToString.Exclude
    private byte[] picture;

    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}

