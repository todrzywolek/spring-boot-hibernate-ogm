package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customerdemographics")
public class CustomerDemographic {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customertypeid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToMany(mappedBy = "demographics")
    private List<Customer> customers = new ArrayList<>();

    private String customerDesc;

    protected CustomerDemographic() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Override
    public String toString() {
        return "CustomerDemographic{" +
                "id=" + id +
                ", customerDesc='" + customerDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDemographic that = (CustomerDemographic) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return customerDesc != null ? customerDesc.equals(that.customerDesc) : that.customerDesc == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customerDesc != null ? customerDesc.hashCode() : 0);
        return result;
    }
}



