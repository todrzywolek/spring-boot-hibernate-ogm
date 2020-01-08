package pl.edu.agh.databases.hibernate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Person {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.PERSIST)
    private Set<Hike> organizedHikes = new HashSet<>();

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Hike> getOrganizedHikes() {
        return organizedHikes;
    }

    public void setOrganizedHikes(Set<Hike> organizedHikes) {
        this.organizedHikes = organizedHikes;
    }
}
