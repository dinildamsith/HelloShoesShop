package lk.ijse.hello_shoes_shop_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.hello_shoes_shop_backend.enums.Category;
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
@Table(name = "supplier")
public class SupplierEntity implements SuperEntity{
    @Id
    private String supplierCode;
    private String supplierName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
//    private String addressLine4;
//    private String addressLine5;
//    private String addressLine6;
    private String contact1;
    private String contact2;
    private String email;

    @JsonIgnore // Research  This Annotation
    @ManyToMany(mappedBy = "supplierEntityList",cascade = CascadeType.REMOVE)
    private List<ItemEntity> itemEntityList;


}
