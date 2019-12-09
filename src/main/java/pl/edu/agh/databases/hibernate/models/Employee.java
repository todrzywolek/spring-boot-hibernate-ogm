package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "employees")
public class Employee {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "employeeid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(length = 20)
    private String lastName;

    @Column(length = 20)
    private String firstName;

    @Column(length = 30)
    private String title;

    @Column(length = 25)
    private String titleOfCourtesy;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

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
    private String homePhone;

    @Column(length = 4)
    private String extension;

    @Column(length = 40)
    private byte[] photo;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private String photoPath;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "reportsto")
    private Employee supervisor;

    @Column(name = "reportsto", insertable = false, updatable = false)
    private Integer reportsTo;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> subordinates;

    @ManyToMany
    @JoinTable(name = "employeeterritories",
            joinColumns = {
                    @JoinColumn(name = "employeeid", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "territoryid", nullable = false, updatable = false)})
    private List<Territory> territories = new ArrayList<>();

    public Employee(UUID id, String lastName, String firstName, String title, Date birthDate, String titleOfCourtesy, Date hireDate,
                    String address, String city, String region, String postalCode, String country, String homePhone, String extension,
                    String notes, Integer reportsTo) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.birthDate = birthDate;
        this.titleOfCourtesy = titleOfCourtesy;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.notes = notes;
        this.reportsTo = reportsTo;
    }

    protected Employee() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public Integer getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", title='" + title + '\'' +
                ", titleOfCourtesy='" + titleOfCourtesy + '\'' +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", extension='" + extension + '\'' +
                ", notes='" + notes + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", supervisor=" + supervisor +
                ", reportsTo=" + reportsTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (title != null ? !title.equals(employee.title) : employee.title != null) return false;
        if (titleOfCourtesy != null ? !titleOfCourtesy.equals(employee.titleOfCourtesy) : employee.titleOfCourtesy != null)
            return false;
        if (birthDate != null ? !birthDate.equals(employee.birthDate) : employee.birthDate != null) return false;
        if (hireDate != null ? !hireDate.equals(employee.hireDate) : employee.hireDate != null) return false;
        if (address != null ? !address.equals(employee.address) : employee.address != null) return false;
        if (city != null ? !city.equals(employee.city) : employee.city != null) return false;
        if (region != null ? !region.equals(employee.region) : employee.region != null) return false;
        if (postalCode != null ? !postalCode.equals(employee.postalCode) : employee.postalCode != null) return false;
        if (country != null ? !country.equals(employee.country) : employee.country != null) return false;
        if (homePhone != null ? !homePhone.equals(employee.homePhone) : employee.homePhone != null) return false;
        if (extension != null ? !extension.equals(employee.extension) : employee.extension != null) return false;
        if (!Arrays.equals(photo, employee.photo)) return false;
        if (notes != null ? !notes.equals(employee.notes) : employee.notes != null) return false;
        if (photoPath != null ? !photoPath.equals(employee.photoPath) : employee.photoPath != null) return false;
        if (supervisor != null ? !supervisor.equals(employee.supervisor) : employee.supervisor != null) return false;
        return reportsTo != null ? reportsTo.equals(employee.reportsTo) : employee.reportsTo == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (titleOfCourtesy != null ? titleOfCourtesy.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        result = 31 * result + (supervisor != null ? supervisor.hashCode() : 0);
        result = 31 * result + (reportsTo != null ? reportsTo.hashCode() : 0);
        return result;
    }
}
