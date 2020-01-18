package pl.edu.agh.databases.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Data
@Entity
@NamedNativeQuery(
        name = "ProductReport",
        query = "db.OrderDetails.aggregate([{'$group': {'_id': '$ProductID', 'total': {'$sum': '$Quantity'}}}])",
        resultClass = ProductReport.class )
public class ProductReport {
    @Id
    @Column(name = "_id")
    private Integer productID;

    @Column(name = "total")
    private Integer totalQuantity;
}
