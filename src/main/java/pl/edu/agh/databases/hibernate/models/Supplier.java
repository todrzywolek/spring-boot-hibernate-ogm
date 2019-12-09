package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
public class Supplier {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "supplierid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(length = 40, nullable = false)
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

    private String homePage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

    protected Supplier() {
    }

    public Supplier(UUID id, String companyName, String contactName, String contactTitle, String address, String city, String region,
                    String postalCode, String country, String phone, String fax, String homePage) {
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
        this.homePage = homePage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Supplier{" +
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
                ", homePage='" + homePage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != null ? !id.equals(supplier.id) : supplier.id != null) return false;
        if (companyName != null ? !companyName.equals(supplier.companyName) : supplier.companyName != null)
            return false;
        if (contactName != null ? !contactName.equals(supplier.contactName) : supplier.contactName != null)
            return false;
        if (contactTitle != null ? !contactTitle.equals(supplier.contactTitle) : supplier.contactTitle != null)
            return false;
        if (address != null ? !address.equals(supplier.address) : supplier.address != null) return false;
        if (city != null ? !city.equals(supplier.city) : supplier.city != null) return false;
        if (region != null ? !region.equals(supplier.region) : supplier.region != null) return false;
        if (postalCode != null ? !postalCode.equals(supplier.postalCode) : supplier.postalCode != null) return false;
        if (country != null ? !country.equals(supplier.country) : supplier.country != null) return false;
        if (phone != null ? !phone.equals(supplier.phone) : supplier.phone != null) return false;
        if (fax != null ? !fax.equals(supplier.fax) : supplier.fax != null) return false;
        return homePage != null ? homePage.equals(supplier.homePage) : supplier.homePage == null;
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
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        return result;
    }
}
