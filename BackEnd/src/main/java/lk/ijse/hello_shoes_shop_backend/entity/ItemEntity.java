package lk.ijse.hello_shoes_shop_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "item")
public class ItemEntity {
    @Id
    private String itemCode;
    private String itemDesc;
    @Column(columnDefinition = "LONGTEXT")
    private String itemPic;
    private String Category;
    private String verities;
    private String occasion;
    private String itemType;
    private double unitPriceSale;
    private double expectedProfit;
    private double profitMargin;
    private String  status;
    private double buyPrice;


    @JsonIgnore // Research  This Annotation

    @ManyToMany(mappedBy = "buyItem",cascade = CascadeType.REMOVE)
    private List<OrderEntity> order;








    @ManyToMany
    @JoinTable(
            name = "supplier_supply_item_details"
    )
    private List<SupplierEntity> supplierEntityList;


    @JsonIgnore // Research  This Annotation
    @OneToMany(mappedBy = "itemEntititys",cascade = CascadeType.REMOVE)
    private List<StockEntity> stockEntityList;

//    @OneToMany(mappedBy = "item")
//    private List<StockEntity> stock;




}
