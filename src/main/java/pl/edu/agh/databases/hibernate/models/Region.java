package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Region {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "regionid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private String regionDescription;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<Territory> territories;

    protected Region() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", regionDescription='" + regionDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (id != null ? !id.equals(region.id) : region.id != null) return false;
        return regionDescription != null ? regionDescription.equals(region.regionDescription) : region.regionDescription == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (regionDescription != null ? regionDescription.hashCode() : 0);
        return result;
    }
}
