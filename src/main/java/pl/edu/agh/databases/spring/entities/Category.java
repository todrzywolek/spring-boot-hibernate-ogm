package pl.edu.agh.databases.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "Categories")
public class Category {

    @Id
    private String categoryID;

    @NonNull
    private String categoryName;

    @NonNull
    private String description;

    @ToString.Exclude
    @JsonIgnore
    private byte[] picture;

    @ToString.Exclude
    @NonNull
    private List<Integer> products;
}

