package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "territories")
public class Territory {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "territoryid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private String territoryDescription;

    @ManyToOne
    @JoinColumn(name = "regionid")
    private Region region;

    @ManyToMany(mappedBy = "territories")
    private List<Employee> employees = new ArrayList<>();

    public Territory() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Territory{" +
                "id=" + id +
                ", territoryDescription='" + territoryDescription + '\'' +
                ", region=" + region +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Territory territory = (Territory) o;

        if (id != null ? !id.equals(territory.id) : territory.id != null) return false;
        if (territoryDescription != null ? !territoryDescription.equals(territory.territoryDescription) : territory.territoryDescription != null)
            return false;
        return region != null ? region.equals(territory.region) : territory.region == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (territoryDescription != null ? territoryDescription.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }
}
