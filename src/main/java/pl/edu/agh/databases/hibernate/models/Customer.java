package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customerid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @ManyToMany
    @JoinTable(name = "customercustomerdemo",
            joinColumns = {
                    @JoinColumn(name = "customerid", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "customertypeid", nullable = false, updatable = false)})
    private List<CustomerDemographic> demographics = new ArrayList<>();

    @Column(length = 40)
    private String companyName;

    @Column(length = 30)
    private String contactName;

    @Column(length = 30)
    private String contactTitle;

    @Column(length = 60)
    private String address;

    @Column(length = 15)
    private String city;

    @Column(length = 15)
    private String region;

    @Column(length = 10)
    private String postalCode;

    @Column(length = 15)
    private String country;

    @Column(length = 24)
    private String phone;

    @Column(length = 24)
    private String fax;

    public Customer(UUID id, String companyName, String contactName, String contactTitle, String address,
                    String city, String region, String postalCode, String country, String phone, String fax) {
        this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
    }

    protected Customer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<CustomerDemographic> getDemographics() {
        return demographics;
    }

    public void setDemographics(List<CustomerDemographic> demographics) {
        this.demographics = demographics;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactTitle='" + contactTitle + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (companyName != null ? !companyName.equals(customer.companyName) : customer.companyName != null)
            return false;
        if (contactName != null ? !contactName.equals(customer.contactName) : customer.contactName != null)
            return false;
        if (contactTitle != null ? !contactTitle.equals(customer.contactTitle) : customer.contactTitle != null)
            return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (city != null ? !city.equals(customer.city) : customer.city != null) return false;
        if (region != null ? !region.equals(customer.region) : customer.region != null) return false;
        if (postalCode != null ? !postalCode.equals(customer.postalCode) : customer.postalCode != null) return false;
        if (country != null ? !country.equals(customer.country) : customer.country != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        return fax != null ? fax.equals(customer.fax) : customer.fax == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (contactTitle != null ? contactTitle.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        return result;
    }
}
