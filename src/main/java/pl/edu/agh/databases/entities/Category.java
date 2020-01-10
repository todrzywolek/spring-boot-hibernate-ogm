package pl.edu.agh.databases.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
public class Category {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String categoryName;
    private String description;
    private byte[] picture;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}

