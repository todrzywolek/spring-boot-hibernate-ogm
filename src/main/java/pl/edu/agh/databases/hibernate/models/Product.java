package pl.edu.agh.databases.hibernate.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "productid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @Column(name = "categoryid", insertable = false, updatable = false)
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "supplierid")
    private Supplier supplier;

    /**
     * Storing both foreign key and referenced entity (both target the very same DB column)
     * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
     */
    @Column(name = "supplierid", insertable = false, updatable = false)
    private Integer supplierId;

    @Column(length = 40)
    private String productName;

    //    FIXME: below doesn't work - doesn't generate table
//    @Column(name = "discontinued", columnDefinition = "INT(1)")
    private Boolean discontinued;

    @Column(length = 20)
    private String quantityPerUnit;

    // TODO: money type
    private float unitPrice;

    private Integer unitsInStock;

    private Integer unitsOnOrder;

    private Integer reorderLevel;

    protected Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(UUID id, Category category, Supplier supplier, String productName, Boolean discontinued, String quantityPerUnit,
                   float unitPrice, Integer unitsInStock, Integer unitsOnOrder, Integer reorderLevel) {
        this.id = id;
        this.category = category;
        this.supplier = supplier;
        this.productName = productName;
        this.discontinued = discontinued;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Integer getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Integer unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", categoryId=" + categoryId +
                ", supplier=" + supplier +
                ", supplierId=" + supplierId +
                ", productName='" + productName + '\'' +
                ", discontinued=" + discontinued +
                ", quantityPerUnit='" + quantityPerUnit + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                ", unitsOnOrder=" + unitsOnOrder +
                ", reorderLevel=" + reorderLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Float.compare(product.unitPrice, unitPrice) != 0) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (categoryId != null ? !categoryId.equals(product.categoryId) : product.categoryId != null) return false;
        if (supplier != null ? !supplier.equals(product.supplier) : product.supplier != null) return false;
        if (supplierId != null ? !supplierId.equals(product.supplierId) : product.supplierId != null) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (discontinued != null ? !discontinued.equals(product.discontinued) : product.discontinued != null)
            return false;
        if (quantityPerUnit != null ? !quantityPerUnit.equals(product.quantityPerUnit) : product.quantityPerUnit != null)
            return false;
        if (unitsInStock != null ? !unitsInStock.equals(product.unitsInStock) : product.unitsInStock != null)
            return false;
        if (unitsOnOrder != null ? !unitsOnOrder.equals(product.unitsOnOrder) : product.unitsOnOrder != null)
            return false;
        return reorderLevel != null ? reorderLevel.equals(product.reorderLevel) : product.reorderLevel == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
        result = 31 * result + (supplierId != null ? supplierId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (discontinued != null ? discontinued.hashCode() : 0);
        result = 31 * result + (quantityPerUnit != null ? quantityPerUnit.hashCode() : 0);
        result = 31 * result + (unitPrice != +0.0f ? Float.floatToIntBits(unitPrice) : 0);
        result = 31 * result + (unitsInStock != null ? unitsInStock.hashCode() : 0);
        result = 31 * result + (unitsOnOrder != null ? unitsOnOrder.hashCode() : 0);
        result = 31 * result + (reorderLevel != null ? reorderLevel.hashCode() : 0);
        return result;
    }
}
